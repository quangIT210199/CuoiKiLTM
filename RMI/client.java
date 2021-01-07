/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author lehuy
 */
public class client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        LocateRegistry.getRegistry(5001);
        IProduct xxx = (IProduct) Naming.lookup("rmi://26.236.58.26:5001/product");
        Product p = xxx.getProduct("xxxx");
        p.setName("xxxxxxxxxxxxxx");
        p.setImportPrice((float) 123.123123);
        p.setExportPrice((float)4123.12312312);
        System.out.println(xxx.insertProduct(p));
    }
}
