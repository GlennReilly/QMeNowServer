package com.bluemongo.springmvcjsontest.controller;


import com.bluemongo.springmvcjsontest.model.QRCodePayload;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;

import utils.InputHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by glenn on 1/10/15.
 */


@Controller
@RequestMapping("/FlexibleUIConfig/barcode")
public class BarcodeController implements ServletContextAware {

    private ServletContext servletContext;

    @RequestMapping(value="/")
    public ModelAndView GetIndexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/Barcode/index");
        return modelAndView;
    }

/*    @RequestMapping(value = "/makeQRCode")
    public ModelAndView MakeQRCode(String txtcontent) throws WriterException, IOException
    {
        int height = 250;
        int width = 250;

        if (txtcontent == null){
            txtcontent = "default txtcontent";
        }

        String fileType = "gif";
        String filePath = "/qrCode.gif";
        File qrFile= new File(servletContext.getRealPath("/") + "/resources/images" + filePath);

        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        String jsonBarcodePayload = getBarcodePayload(txtcontent);

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

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/Barcode/index");
        return modelAndView;
    }*/

    private String getBarcodePayload(String txtcontent) {

        String formattedNow = getFormattedNowDateString();

        QRCodePayload QRCodePayload = new QRCodePayload();
        QRCodePayload.setBusinessName("test customer name");
        QRCodePayload.setDateTimeString(formattedNow);
        QRCodePayload.setContent(txtcontent);
        Gson gson = new Gson();
        String jsonBarcodePayload = gson.toJson(QRCodePayload);

        return jsonBarcodePayload;

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
