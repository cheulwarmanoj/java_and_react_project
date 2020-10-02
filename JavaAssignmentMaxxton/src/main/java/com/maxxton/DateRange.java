package com.maxxton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRange implements Comparable<DateRange> {

	private String startDate;
	private String endDate;
	
	
	public String getStartDate() {
		return startDate;
	}	
	public String getEndDate() {
		return endDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public DateRange(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	 @Override
     public boolean equals(Object obj) {
         // TODO Auto-generated method stub
		 DateRange inteval = (DateRange)obj;
         return this.getStartDate().equals(inteval.getStartDate()) && this.getEndDate().equals(inteval.getEndDate()) ;
     }

 @Override
 public int compareTo(DateRange o) {
     // TODO Auto-generated method stub

     SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
     try{
         Date startDate1  = sdf.parse(startDate.replaceAll("\\s", ""));
         Date endDate1  = sdf.parse(endDate.replaceAll("\\s", ""));
         Date pstartDate  = sdf.parse(o.startDate.replaceAll("\\s", ""));
         Date pendDate  = sdf.parse(o.endDate.replaceAll("\\s", ""));


         return startDate1.compareTo(pstartDate);

     }catch(Exception ex){
         ex.printStackTrace();
     }
     return 0;
 }
	
}
