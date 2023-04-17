package com.example.addressbook.service;

import com.example.addressbook.DAO.AddressDAO;
import com.example.addressbook.DTO.AddressDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
public class AddressService {

    static final int LISTCOUNT = 5;

    private static AddressService instance;

    private AddressService() {

    }

    public static AddressService getInstance() {
        if (instance == null) {
            instance = new AddressService();
        }
        return instance;
    }

    public boolean addDB(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("addDB...");

        AddressDAO dao = AddressDAO.getInstance();

        AddressDTO addr = new AddressDTO();

        addr.setAb_name(request.getParameter("ab_name"));
        addr.setAb_email(request.getParameter("ab_email"));
        addr.setAb_comdept(request.getParameter("ab_comdept"));
        addr.setAb_birth(request.getParameter("ab_birth"));
        addr.setAb_tel(request.getParameter("ab_tel"));
        addr.setAb_memo(request.getParameter("ab_memo"));

        return dao.insertDB(addr);
    }

    public List<AddressDTO> getList() throws SQLException, ClassNotFoundException {
        log.info("getList()...");

        AddressDAO dao = AddressDAO.getInstance();
        List<AddressDTO> addressList = dao.getList();

        return addressList;
    }

    public void getOne(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("getOne()...");
        AddressDAO dao = AddressDAO.getInstance();

        int ab_id = Integer.parseInt(request.getParameter("ab_id"));

        AddressDTO address = dao.getAddressOne(ab_id);

        request.setAttribute("ab_id", ab_id);
        request.setAttribute("address", address);
    }

    public boolean modifyForm(HttpServletRequest request) throws Exception {
        AddressDAO dao = AddressDAO.getInstance();

        AddressDTO address = new AddressDTO();

        int ab_id = (Integer) request.getSession().getAttribute("sessionMemberId");

        address.setAb_id(ab_id);
        address.setAb_name(request.getParameter("ab_name"));
        address.setAb_email(request.getParameter("ab_email"));
        address.setAb_tel(request.getParameter("ab_tel"));
        address.setAb_birth(request.getParameter("ab_birth"));
        address.setAb_comdept(request.getParameter("ab_comdept"));
        address.setAb_memo(request.getParameter("ab_memo"));

        return dao.modifyAddress(address);
    }

    public boolean removeAction(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("delete()...");
        AddressDAO boardDAO = AddressDAO.getInstance();
        int ab_id = Integer.parseInt(request.getParameter("ab_id"));
        return boardDAO.removeAction(ab_id);
    }
}
