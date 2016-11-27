package com.xc.as.core.crawler;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxc on 2016/11/26.
 */
public class CrawlerScaner {
    //继承CrawlerInterface接口的视为合法爬虫
    private static Class<?> crawlerInterface = CrawlerInterface.class;

    public static List<Class<?>>  getCurrentPkgClass() throws ClassNotFoundException{
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String pkgName = CrawlerScaner.class.getPackage().getName();
        String path = pkgName.replace('.', '/');
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(path);

        File dir =new File( url.getFile());
        for(File f: dir.listFiles()){
            String name = f.getName();
            if(name.endsWith(".class")){
                classes.add(Class.forName(pkgName + "." + name.substring(0, name.length() - 6)));
            }
        }
        return classes;
    }

    public static List<Class<?>> getAllAssignedClass() throws ClassNotFoundException{
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

    public static void main(String [] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        CrawlerScaner cs = new CrawlerScaner();
        List<Class<?>> classList = cs.getAllAssignedClass();
        for(Class<?> c : classList){
            System.out.println(c.getName());
        }
    }
}
