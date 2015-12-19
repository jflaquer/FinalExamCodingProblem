package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import util.HibernateUtil;

public class RateDAL {

	public static double getRate(int GivenCreditScore) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RateDomainModel ratGet = null;
		double Rate;
		
			tx = session.beginTransaction();

			Query query = session.createQuery("from RateDomainModel where MinCreditScore = :CS ");
			query.setParameter("CS", GivenCreditScore);

			ratGet = (RateDomainModel) query.list().get(0);

			tx.commit();
			
			Rate = ratGet.getInterestRate();
		
			session.close();
		return Rate;
	}
	
	public static double MortageCalc(double rate, int years, double costOfHouse, boolean bool, int fv){
		double rp = rate/(100*12);
		double yrs = years*12;
		
		return (FinanceLib.pmt(rp, yrs, costOfHouse, 0, false)*(-1));
	}

}