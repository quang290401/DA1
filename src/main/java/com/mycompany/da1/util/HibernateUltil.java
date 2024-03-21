package com.mycompany.da1.util;

import com.mycompany.da1.entity.*;
import org.hibernate.SessionFactory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUltil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=duan1");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "quang@201");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update"); // Hoặc "update" tùy vào nhu cầu của bạn

        conf.setProperties(properties);
        conf.addAnnotatedClass(SanPhamEntity.class);
        conf.addAnnotatedClass(MauSacEntity.class);
        conf.addAnnotatedClass(NhaSanXuatEntity.class);
        conf.addAnnotatedClass(DanhMucEntity.class);
        conf.addAnnotatedClass(ChatLieuEntity.class);
        conf.addAnnotatedClass(SanPhamChiTietEntity.class);
        conf.addAnnotatedClass(VaiTroEntity.class);
        conf.addAnnotatedClass(TaiKhoanEntity.class);
        conf.addAnnotatedClass(KichCoEntity.class);
        conf.addAnnotatedClass(KhachHangEntity.class);
        conf.addAnnotatedClass(VoucherEntity.class);
        conf.addAnnotatedClass(HoaDonEntity.class);
        conf.addAnnotatedClass(HoaDonChiTietEntity.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }
}
