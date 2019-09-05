package com;


import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;

public class Test {

	public static void main(String[] args) {
		 try {

		     //axis1.4客户端发送报文方法
			    String xmlStr="";
	            String result2= invoke("http://localhost:7777/axis/services/StudentService","getStudentInfo",xmlStr);
	            System.out.println(result2);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	}
	
    /*
     * 调用webservice路口
     * @param endpoint 地址
     * @param methodName 调用的方法
     * @param xmlStr 传递的xml字符串参数
     * @return
     */
    public static String invoke(String endpoint, String methodName, String xmlStr) {
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        QName qn = new QName(methodName);
        call.setOperationName(qn);
        call.setTargetEndpointAddress(endpoint);
        call.setUseSOAPAction(true);
        String result = "";
        try {
            // 给方法传递参数，并且调用方法
            result = (String) call.invoke(new Object[]{xmlStr});
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }
}
