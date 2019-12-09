package com.jiang.demo;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author jiang
 * @date 2019/12/5
 * @time 15:15
 */
public class Count {

    @Test
    public void x() {
        BigInteger des = new BigInteger("45612315641321565453216513215632153215");

        BigInteger start = des.divide(BigInteger.valueOf(2));

        while (start!= des){
            BigInteger pow = getPow(start);
            if (pow.compareTo(des) > 0){
                start = getHalf(start);
            }
        }

    }

    private BigInteger getPow(BigInteger start){
        return start.pow(2);
    }
    private BigInteger getDouble (BigInteger s){
        return s.add(s);
    }
    private BigInteger getHalf(BigInteger s){
        return  s.divide(BigInteger.valueOf(2));
    }
}
