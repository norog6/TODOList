package com.solbeg.repository;

import com.solbeg.model.Task;
import com.solbeg.model.TaskStatus;
import com.solbeg.util.HibernateUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TaskRepository {

    public List<Task> getActiveTasksSortedByDate() {
        TypedQuery<Task> query = HibernateUtil.getSessionFactory().createEntityManager().createQuery("SELECT t FROM Task t WHERE t.status = :status ORDER BY t.createdAt DESC", Task.class);
        query.setParameter("status", TaskStatus.ACTIVE);
        return query.getResultList();
    }


    public List<Task> getCompletedTasksSortedByDate() {
        TypedQuery<Task> query = HibernateUtil.getSessionFactory().createEntityManager().createQuery("SELECT t FROM Task t WHERE t.status = :status ORDER BY t.createdAt DESC", Task.class);
        query.setParameter("status", TaskStatus.COMPLETED);
        return query.getResultList();
    }

    public void saveTask(Task task) {
        task.setCreatedAt(new Date());
        task.setUpdatedAt(new Date());
        task.setStatus(TaskStatus.ACTIVE);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(task);
        session.getTransaction().commit();
    }

    public void updateTask(Long id) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var task = session.find(Task.class, id);
            task.setStatus(TaskStatus.COMPLETED);
            task.setUpdatedAt(new Date());
            session.getTransaction().commit();
        }
    }

    public void deleteTask(Long id) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var task = session.find(Task.class, id);
            task.setStatus(TaskStatus.REMOVED);
            session.getTransaction().commit();
        }
    }
}
