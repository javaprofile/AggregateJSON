package com.aggregate;


/**
 * This aggregation app reads json from URL, creates JSON object 
 * and aggregates data.
 *
 */
public class AggregationApp 
{
    public static void main( String[] args )
    {
       Aggregation aggregation = new Aggregation();
       aggregation.performAggregationOnJSON();
    }
}
