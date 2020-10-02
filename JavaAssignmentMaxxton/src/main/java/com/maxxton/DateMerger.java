package com.maxxton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
// this class will merge the given date range 
public class DateMerger {

	public static void main(String[] args) {

		ArrayList<DateRange> dateRanges = new ArrayList<DateRange>();

		dateRanges.add(new DateRange("01 Jan 2014", "30 Jan 2014"));
		dateRanges.add(new DateRange("15 Jan 2014", "15 Feb 2014"));
		dateRanges.add(new DateRange("10 Mar 2014", "15 Apr 2014"));
		dateRanges.add(new DateRange(" 10 Apr 2014", "15 May 2014"));

		System.out.println("Following are the new merged date range ");
		for (Iterator iterator = mergeDates(dateRanges).iterator(); iterator.hasNext();) {
			DateRange interval = (DateRange) iterator.next();

			System.out.println("from "+ interval.getStartDate() + " to " + interval.getEndDate());
		}

	}

	public static List<DateRange> mergeDates(List<DateRange> dateRanges) {

		/*
		 * Sort the dateRanges , DateRange class have implemented Comparable Interface.
		 * So we will get sorted intervals. DateRanges sorted based on start of interval
		 */
		Collections.sort(dateRanges);
		Set<DateRange> resultdateRanges = new TreeSet<DateRange>();

		List<DateRange> mergedDateRanges = new ArrayList<DateRange>();

		// declare date format to parse and format date from string to and from
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
		if (dateRanges.size() == 1) {
			// resultdateRanges = dateRanges
			return dateRanges;
		}
		if (dateRanges.size() > 1) {
			// get first interval Object. conside it as first interval
			DateRange mergeDateRange = dateRanges.get(0);

			// loop other intervals from second in the dateRanges
			for (int i = 1; i < dateRanges.size(); i++) {

				DateRange interval2 = dateRanges.get(i);
				try {

					Date startDate1 = sdf.parse(mergeDateRange.getStartDate().replaceAll("\\s", ""));
					Date endDate1 = sdf.parse(mergeDateRange.getEndDate().replaceAll("\\s", ""));

					Date startDate2 = sdf.parse(interval2.getStartDate().replaceAll("\\s", ""));
					Date endDate2 = sdf.parse(interval2.getEndDate().replaceAll("\\s", ""));

					// compare if current interval's start date is before merging interval's end
					// date
					// then the two intervals are overlaping
					if (startDate2.compareTo(endDate1) < 0) {

						// check whether end date of current loop interval is after the merging
						// interval.
						// then we need to update the end date of merging interval with looping
						// interval's end date
						if (endDate2.compareTo(endDate1) > 0) {

							mergeDateRange.setEndDate(interval2.getEndDate());

						}
					} else {
						// compare if current interval's start date is after merging interval's end date
						// then it must be a new interval start so swap mergDateRange variable with
						// current looping interval

						mergeDateRange = interval2;

					}

					// add merge interval to set.
					resultdateRanges.add(mergeDateRange);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		}
		mergedDateRanges.addAll(resultdateRanges);
		return mergedDateRanges;

	}

}
