package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.BarcodePayload;
import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import utils.InputHelper;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by glenn on 17/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/business")
public class BusinessController implements ServletContextAware
{
        private ServletContext servletContext;

    @RequestMapping(value = "/{businessId}", method = RequestMethod.GET)
    public ModelAndView ShowBusinessDetails(@PathVariable int businessId, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/Business/BusinessDetails");
        Business business = new BusinessStore().get(businessId);
        httpSession.setAttribute("businessId", business.getId());
        modelAndView.addObject("command", business);
        modelAndView.addObject("businessName", business.getBusinessName());
        modelAndView.addObject("logoName", business.getLogoName());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{businessId}", method = RequestMethod.GET)
    public ModelAndView EditBusinessDetails(@PathVariable int businessId, HttpSession httpSession){
        ModelAndView modelAndView = getModelAndViewForBusinessDetailsForm(businessId, httpSession);
        return modelAndView;
    }

    private ModelAndView getModelAndViewForBusinessDetailsForm(@PathVariable int businessId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Edit Business Details");
        modelAndView.setViewName("FlexibleUIConfig/Business/addEditBusinessForm");
        Business business = new BusinessStore().get(businessId);
        httpSession.setAttribute("businessId", business.getId()); //TODO should this be user.businessId? What about Admin editing other businesses?
        modelAndView.addObject("command", business);
        modelAndView.addObject("businessName", business.getBusinessName());
        modelAndView.addObject("logoName", business.getLogoName());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView GetBusinessAddForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FlexibleUIConfig/Business/addEditBusinessForm");
        modelAndView.addObject("command", new Business());
        return modelAndView;
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public String AddBusiness(@ModelAttribute Business business, HttpSession httpSession) {
        if(httpSession.getAttribute("businessId")==null || httpSession.getAttribute("businessId").equals(0)) {
            int newBusinessId = business.saveNew();
            return "Business saved successfully: " + newBusinessId;
        }
        else{
            int businessId = Integer.parseInt(httpSession.getAttribute("businessId").toString());

            business.setId(businessId);
            business.saveUpdate();
            return "Business updated successfully";
        }
    }

    @RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
    public ModelAndView UploadLogo(@RequestParam("logo") MultipartFile logo, HttpSession httpSession){
        ModelAndView modelAndView;

        if(!httpSession.getAttribute("businessId").equals(null)) {
            int businessId = Integer.parseInt(httpSession.getAttribute("businessId").toString());
            Business business = new BusinessStore().get(businessId);
            int maxFileSizeBytes = 200000;

            if (!logo.isEmpty()) {
                if (logo.getSize() < maxFileSizeBytes) {
                    if (logo.getContentType().contains("image")) {
                        String fileName = business.getBusinessName() + "_" + business.getId() + "_" + logo.getOriginalFilename();
                        String filePathAndName = "/" + fileName;
                        String imageRelativePath = "/resources/images" + filePathAndName;
                        File logoFile = new File(servletContext.getRealPath("/") + imageRelativePath);

                        try {
                            logo.transferTo(logoFile);
                            business.setLogoName(fileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //return "You successfully uploaded your logo.";
                        modelAndView = getModelAndViewForBusinessDetailsForm(businessId, httpSession);
                        modelAndView.addObject("pageMessage", "You successfully uploaded your logo.");

                    } else {
                        //return "Sorry, that's not an image.";
                        modelAndView = ModelViewHelper.GetModelViewForError("Sorry, that's not an image.");
                    }
                }
                else{
                    //return "Sorry, that file is larger than the allowed " + maxFileSizeBytes/1000 + "KB";
                    modelAndView = ModelViewHelper.GetModelViewForError("Sorry, that file is larger than the allowed " + maxFileSizeBytes/1000 + "KB");
                }
            }
            else{
                //return "You failed to upload because the file was empty.";
                modelAndView = ModelViewHelper.GetModelViewForError("You failed to upload because the file was empty.");
            }
        }
        else{
            //return "Sorry, but businessId found";
            modelAndView = ModelViewHelper.GetModelViewForError("Sorry, but businessId found");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/barcode")
    public ModelAndView getBarcodeForBusiness(HttpSession httpSession) throws WriterException, IOException {
        int height = 250;
        int width = 250;
        ModelAndView modelAndView = new ModelAndView();

        if (httpSession.getAttribute("User") != null) {
            User user = (User)httpSession.getAttribute("User");
            int businessId = user.getBusinessId(); //TODO need to work on whether we use the currently logged in users businessId or a separate one. What about admin editing?
            //TODO maybe each business would have a separate user just for showing the QRCode?
            if (businessId > 0){
                httpSession.setAttribute("businessId", businessId);
            }
        }

        if (httpSession.getAttribute("businessId") != null) {
            int businessId = Integer.parseInt(httpSession.getAttribute("businessId").toString());
            Business business = new BusinessStore().get(businessId);

            String fileType = "gif";
            String fileName = "qrCode" + "_" + business.getBusinessName() + "_" + businessId;
            String filePath = "/" + fileName + ".gif";
            String imageRelativePath = "/resources/images" + filePath;
            File qrFile = new File(servletContext.getRealPath("/") + imageRelativePath);
            modelAndView.addObject("QRCodePath", imageRelativePath);
            modelAndView.addObject("Business", business);

            Hashtable hintMap = new Hashtable();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            String jsonBarcodePayload = getBarcodePayload(business);

        /*try {*/
            BitMatrix byteMatrix = qrCodeWriter.encode(jsonBarcodePayload, BarcodeFormat.QR_CODE, width, height, hintMap);
            // Make the BufferedImage to hold the QRCode
            int matrixWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            // Paint and saveNew the image using the ByteMatrix
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, qrFile);

/*        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


            modelAndView.setViewName("/FlexibleUIConfig/Barcode/index");
        }
        else{
            modelAndView = ModelViewHelper.GetModelViewForError("No businessId found, please try again.");
        }

        return modelAndView;
    }

    private String getBarcodePayload(Business business) {

        String formattedNow = getFormattedNowDateString();

        BarcodePayload barcodePayload = new BarcodePayload();
        barcodePayload.setCustomerName(business.getBusinessName());
        barcodePayload.setDateTimeString(formattedNow);
        barcodePayload.setContent(business.getPhysicalAddress());
        Gson gson = new Gson();
        String jsonBarcodePayload = gson.toJson(barcodePayload);

        return jsonBarcodePayload;

/*

        return JsonConvert.SerializeObject(barcodePayload);*/
    }

    private String getFormattedNowDateString() {
        //"yyyy-MM-dd'T'HH:mm:ssz" ISO8601
        //2015-10-10T11:16+00:00

        Date now = Calendar.getInstance().getTime();
        String dateString = InputHelper.getISO8601StringFromDate(now);
        return dateString;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


}
