package com.creativosoft.examify.helpers;

import com.creativosoft.examify.models.ArrangementRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBHandler {

    // Creating session from above session factory.
    private Session session;

    // Creating session factory based on above configuration.
    private SessionFactory sessionFactory;

    public DBHandler() {
        // Creating configuration based on ArrangementRecord class.
        Configuration configuration = new Configuration().configure().addAnnotatedClass(ArrangementRecord.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    private void initializeSession() {
        // Initializing session.
        session = sessionFactory.openSession();
    }

    public Session openSession() {
        initializeSession();
        return session;
    }
}