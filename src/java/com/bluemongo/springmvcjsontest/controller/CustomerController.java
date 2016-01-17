package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.BarcodePayload;
import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import com.bluemongo.springmvcjsontest.service.GetFindCustomerHelper;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by glenn on 17/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/customer")
public class CustomerController implements ServletContextAware
{
    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    // Customer methods

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetCustomerAddForm(HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            modelAndView = ModelViewHelper.GetModelViewForCustomerAdd(httpSession);
        }
        return modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView AddCustomer(@ModelAttribute Customer customer, HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            customer.setBusinessId(user.getBusinessId());
            int newCustomerId = customer.saveNew();
            String message = "Customer saved successfully: " + newCustomerId;
            modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView GetFindCustomerForm(HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetModelViewForFindCustomer(user, null);
            Business business = new BusinessStore().get(user.getBusinessId());
            modelAndView.addObject("businessName", business.getBusinessName());
            modelAndView.addObject("logoName", business.getLogoName());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView FindCustomer(HttpSession httpSession, @ModelAttribute GetFindCustomerHelper getFindCustomerHelper){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            modelAndView = new ModelAndView();
            Business business = new BusinessStore().get(user.getBusinessId());
            modelAndView.addObject("businessName", business.getBusinessName());
            modelAndView.addObject("logoName", business.getLogoName());
            String customerIdStr = getFindCustomerHelper.getCustomerIdStr();
            String firstName = getFindCustomerHelper.getFirstName();
            String lastName = getFindCustomerHelper.getLastName();
            List<Customer> customerList = new CustomerStore().get(user.getBusinessId(), customerIdStr, firstName, lastName);
            if (customerList.size() > 0){
                if (customerList.size() > 1){
                    //more than one matching customers
                    modelAndView.addObject("customerList", customerList);
                    modelAndView.addObject("pageTitle", "Customer List");
                    modelAndView.setViewName("FlexibleUIConfig/Customer/customerList");
                }else{
                    //one customer
                    modelAndView.addObject("command", customerList.get(0));
                    modelAndView.addObject("pageTitle", "Customer Details");
                    modelAndView.setViewName("FlexibleUIConfig/Customer/customerDetails");
                    httpSession.setAttribute("CurrentlyEditingCustomerId", customerList.get(0).getId());
                }
            }else{
                modelAndView = ModelViewHelper.GetModelViewForError("No matching customers found.");
            }

        }
        return modelAndView;
    }

    @RequestMapping(value = "/details/{customerId}", method = RequestMethod.GET)
    public ModelAndView ShowCustomerDetails(HttpSession httpSession, @PathVariable int customerId){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            Customer customer = new CustomerStore().get(user.getBusinessId(), customerId);
            if (customer != null){
                modelAndView = getModelViewForCustomerEdit(httpSession, user, customer);
            }else {
                modelAndView = ModelViewHelper.GetModelViewForError("Sorry, there was an error retrieving that customer.");
            }
        }

        return modelAndView;
    }

    private static ModelAndView getModelViewForCustomerEdit(HttpSession httpSession, User user, Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        Business business = new BusinessStore().get(user.getBusinessId());
        modelAndView.addObject("businessName", business.getBusinessName());
        modelAndView.addObject("logoName", business.getLogoName());
        modelAndView.addObject("command", customer);
        modelAndView.addObject("pageTitle", "Customer Details");
        modelAndView.setViewName("FlexibleUIConfig/Customer/customerDetails");
        httpSession.setAttribute("CurrentlyEditingCustomerId", customer.getId());
        return modelAndView;
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ModelAndView UpdateCustomer(HttpSession httpSession, Customer customer){
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            User user = (User) httpSession.getAttribute("User");
            if (httpSession.getAttribute("CurrentlyEditingCustomerId") != null){
                int currentlyEditingCustomerId = Integer.parseInt(httpSession.getAttribute("CurrentlyEditingCustomerId").toString());
                customer.setId(currentlyEditingCustomerId);
                new CustomerStore().saveUpdate(customer);
                modelAndView = getModelViewForCustomerEdit(httpSession, user, customer);
                modelAndView.addObject("message", "Customer updated successfully.");
                httpSession.setAttribute("CurrentlyEditingCustomerId", null);
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/barcode/{customerId}")
    public ModelAndView getBarcodeForCustomerId(HttpSession httpSession, @PathVariable Integer customerId) throws WriterException, IOException {
        int height = 100;
        int width = 250;
        ModelAndView modelAndView = new ModelAndView();

        if (httpSession.getAttribute("User") != null) {
            User user = (User)httpSession.getAttribute("User");
            int businessId = user.getBusinessId();
            if (businessId > 0){
                httpSession.setAttribute("businessId", businessId);
            }
        }

        if (httpSession.getAttribute("businessId") != null) {
            int businessId = Integer.parseInt(httpSession.getAttribute("businessId").toString());
            Business business = new BusinessStore().get(businessId);

            String fileType = "gif";
            String fileName = "barcode" + "_" + customerId;
            String filePath = "/" + fileName + ".gif";
            String imageRelativePath = "/resources/images" + filePath;
            File qrFile = new File(servletContext.getRealPath("/") + imageRelativePath);
            modelAndView.addObject("QRCodePath", imageRelativePath);
            modelAndView.addObject("Business", business);

            Hashtable hintMap = new Hashtable();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Code39Writer code39Writer = new Code39Writer();

            BitMatrix byteMatrix = code39Writer.encode(customerId.toString(), BarcodeFormat.CODE_39, width, height, hintMap);
            // Make the BufferedImage to hold the barcode
            int matrixWidth = byteMatrix.getWidth();
            int matrixHeight = byteMatrix.getHeight();
            BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixHeight);
            // Paint and saveNew the image using the ByteMatrix
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixHeight; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, qrFile);

            modelAndView.setViewName("/FlexibleUIConfig/Barcode/index");
        }
        else{
            modelAndView = ModelViewHelper.GetModelViewForError("No businessId found, please try again.");
        }

        return modelAndView;
    }


}
