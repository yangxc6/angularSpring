package com.xc.as.core.crawler;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by yxc on 2016/11/26.
 */
public class CrawlerScaner {
    //继承CrawlerInterface接口的视为合法爬虫
    private static Class<?> crawlerInterface = CrawlerInterface.class;

    public static List<Class<?>>  getCurrentPkgClass() throws ClassNotFoundException, IOException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String pkgName = CrawlerScaner.class.getPackage().getName();
        String path = pkgName.replace('.', '/');
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url= classloader.getResource(path);

        //判断是本地文件还是jar文件
        String protocol = url.getProtocol();
        if("file".equals(protocol)) {
            File dir = new File(url.getFile());
            for (File f : dir.listFiles()) {
                String name = f.getName();
                if (name.endsWith(".class")) {
                    classes.add(Class.forName(pkgName + "." + name.substring(0, name.length() - 6)));
                }
            }
        }else if("jar".equals(protocol)){
            JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
            JarFile jarFile = jarURLConnection.getJarFile();

            Enumeration<JarEntry> jarEntries = jarFile.entries();
            while (jarEntries.hasMoreElements()) {
                JarEntry jarEntry = jarEntries.nextElement();
                String jarEntryName = jarEntry.getName();
                if(jarEntryName.startsWith(path) && jarEntryName.endsWith(".class")){
                    String name = jarEntry.getName().replace('/', '.');
                    classes.add(Class.forName(name.substring(0, name.length() - 6)));
                }
            }
        }
        return classes;
    }

    public static List<Class<?>> getAllAssignedClass() throws ClassNotFoundException, IOException {
        //继承crawlerSuperClass的视为合法爬虫
        List<Class<?>> classList = getCurrentPkgClass();
        List<Class<?>> result = new ArrayList<Class<?>>();
        for(Class<?> c : classList){
            if( crawlerInterface.isAssignableFrom(c) && !crawlerInterface.equals(c)){
                result.add(c);
            }
        }
        return result;
    }

    public static void main(String [] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        CrawlerScaner cs = new CrawlerScaner();
        List<Class<?>> classList = cs.getAllAssignedClass();
        for(Class<?> c : classList){
            System.out.println(c.getName());
        }
    }
}
