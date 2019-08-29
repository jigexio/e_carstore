package com.jigexio.store.service.imp;

import com.jigexio.store.dao.CustomerDao;
import com.jigexio.store.dao.imp.CustomerDaoImpJdbc;
import com.jigexio.store.domain.Customer;
import com.jigexio.store.service.CustomerService;
import com.jigexio.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService {

    CustomerDao customerDao = new CustomerDaoImpJdbc();

    @Override
    public boolean login(Customer customer) {

        Customer dbCustomer = customerDao.findByPk(customer.getId());

        if (dbCustomer != null &&
                dbCustomer.getPassword().equals(customer.getPassword())) {

            // 把db返回的客户信息返回给表示层
            customer.setName(dbCustomer.getName());
            customer.setBirthday(dbCustomer.getBirthday());
            customer.setAddress(dbCustomer.getAddress());
            customer.setPhone(dbCustomer.getPhone());

            return true;
        }

        return false;
    }

    @Override
    public void register(Customer customer) throws ServiceException {

        Customer dbCustomer = customerDao.findByPk(customer.getId());

        if (dbCustomer != null) { //客户ID已经存在了
            throw new ServiceException("客户ID: " + customer.getId() + "已经存在！");
        }

        // 注册开始
        customerDao.create(customer);
    }
}
