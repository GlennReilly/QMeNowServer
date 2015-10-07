package com.bluemongo.springmvcjsontest.controller;


import com.bluemongo.springmvcjsontest.model.BarcodePayload;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by glenn on 1/10/15.
 */


@Controller
@RequestMapping("/FlexibleUIConfig/Barcode")
public class BarcodeController implements ServletContextAware {

    private ServletContext servletContext;

    @RequestMapping(value="/")
    public ModelAndView GetIndexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/Barcode/index");
        return modelAndView;
    }

    @RequestMapping(value = "/makeQRCode")
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

        /*try {*/
            BitMatrix byteMatrix = qrCodeWriter.encode(jsonBarcodePayload, BarcodeFormat.QR_CODE, width, height, hintMap);
            // Make the BufferedImage to hold the QRCode
            int matrixWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            // Paint and save the image using the ByteMatrix
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

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/Barcode/index");
        return modelAndView;
    }

    private String getBarcodePayload(String txtcontent) {
        Date now = Calendar.getInstance().getTime();
        String format = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String formattedNow = simpleDateFormat.format(now);

        BarcodePayload barcodePayload = new BarcodePayload();
        barcodePayload.setCustomerName("test customer name");
        barcodePayload.setDateTimeFormat(format);
        barcodePayload.setDateTimeString(formattedNow);
        barcodePayload.setContent(txtcontent);
        Gson gson = new Gson();
        String jsonBarcodePayload = gson.toJson(barcodePayload);

        return jsonBarcodePayload;

/*

        return JsonConvert.SerializeObject(barcodePayload);*/
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
