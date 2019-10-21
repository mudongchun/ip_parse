package com.pt.ip;

public class IpParseTest {
    public static void main(String[] args) {
        IpParse ipParse = new IpParse();
        String res = ipParse.evaluate("128.101.101.101");
        System.out.println(res);
    }
}
