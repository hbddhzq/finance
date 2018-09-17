package com.atguigu.finance.service;

import com.atguigu.finance.bean.NplmBorrowerInfo;
import com.atguigu.finance.bean.NplmContractAttribute;
import com.atguigu.finance.bean.NplmLoanContract;
import com.atguigu.finance.dao.BorrowerInfoMapper;
import com.atguigu.finance.dao.ContractAttributeMapper;
import com.atguigu.finance.dao.LoanContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private BorrowerInfoMapper borrowerInfoMapper;

    @Autowired
    private ContractAttributeMapper contractAttributeMapper;

    @Autowired
    private LoanContractMapper loanContractMapper;


    @Override
    public void testssm() {
        NplmBorrowerInfo borrowerInfo = new NplmBorrowerInfo();
        borrowerInfo.setId(null);
        borrowerInfo.setApplyClientId(3333);

        borrowerInfoMapper.insert(borrowerInfo);
    }

    @Override
    public List<NplmLoanContract> contractList() {
        List<NplmLoanContract> loanContractList = loanContractMapper.selectAll();
        for (NplmLoanContract loanContract : loanContractList) {
            String borrowerId = loanContract.getBorrowerId();
            String id = loanContract.getId();
            Example example1 = new Example(NplmBorrowerInfo.class);
            Example example2 = new Example(NplmContractAttribute.class);
            example1.createCriteria().andEqualTo("id",borrowerId);
            example2.createCriteria().andEqualTo("loanContractId",id);
            NplmBorrowerInfo borrowerInfo = borrowerInfoMapper.selectOneByExample(example1);
            NplmContractAttribute contractAttribute = contractAttributeMapper.selectOneByExample(example2);
            loanContract.setBorrowerInfo(borrowerInfo);
            loanContract.setContractAttribute(contractAttribute);

        }


        return loanContractList;
    }
}
