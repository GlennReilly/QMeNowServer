package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.BarcodePayload;
import com.bluemongo.springmvcjsontest.model.Business;
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

    // Customer methods

    @RequestMapping(value = "/{businessId}", method = RequestMethod.GET)
    public ModelAndView ShowBusinessDetails(@PathVariable int businessId, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/Business/BusinessDetails");
        Business business = new BusinessStore().get(businessId);
        httpSession.setAttribute("businessId", business.getId());
        modelAndView.addObject("command", business);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView GetBusinessAddForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FlexibleUIConfig/Business/addBusinessForm");
        modelAndView.addObject("command", new Business());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String AddBusiness(@ModelAttribute Business business) {
        int newBusinessId = business.saveNew();
        return "Business saved successfully: " + newBusinessId;
    }

    @RequestMapping(value = "/barcode")
    public ModelAndView getBarcodeForBusiness(HttpSession httpSession) throws WriterException, IOException {
        int height = 250;
        int width = 250;
        ModelAndView modelAndView = new ModelAndView();

        if (httpSession.getAttribute("businessId") != null) {
            int businessId = Integer.parseInt(httpSession.getAttribute("businessId").toString());
            Business business = new BusinessStore().get(businessId);

            String fileType = "gif";
            String filePath = "/qrCode.gif";
            File qrFile = new File(servletContext.getRealPath("/") + "/resources/images" + filePath);

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
