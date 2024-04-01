package com.sci.dao;

import com.sci.criteria.FilterQuery;
import com.sci.criteria.Operator;
import com.sci.models.Job;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DBJob {

    public List<Job> get() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Job").list();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public Job get(Integer jobId) {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.get(Job.class, jobId);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }


    public Integer insert(Job job) {

        Transaction transaction = null;
        int jobId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            jobId = (Integer) session.save(job);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }

        return jobId;
    }

    public void update(Job job) {

        Transaction transaction = null;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            session.update(job);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }
    }

    public void delete(Integer jobId) {

        Transaction transaction = null;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            Job job = get(jobId);

            session.delete(job);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }
    }

    public List<Job> getByFilter(List<FilterQuery> filterQueries) {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Job> cr = cb.createQuery(Job.class);
            Root<Job> root = cr.from(Job.class);
            Predicate[] predicates = new Predicate[filterQueries.size()];
            for (int i = 0; i < filterQueries.size(); i++) {
                if (filterQueries.get(i).getOp() == Operator.EQ) {
                    predicates[i] = cb.equal(root.get(filterQueries.get(i).getAttributeName()),
                            filterQueries.get(i).getAttributeValue());
                } else if (filterQueries.get(i).getOp() == Operator.GT) {
                    predicates[i] = cb.gt(root.get(filterQueries.get(i).getAttributeName()),
                            (Integer) filterQueries.get(i).getAttributeValue());
                }
            }
            cr.select(root).where(predicates);
            Query<Job> query = session.createQuery(cr);
            return query.getResultList();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return new ArrayList<>();
    }

}
