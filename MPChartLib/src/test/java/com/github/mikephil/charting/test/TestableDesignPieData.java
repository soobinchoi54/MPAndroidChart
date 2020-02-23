package com.github.mikephil.charting.test;

import com.github.mikephil.charting.data.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by philipp on 06/06/16.
 */
public class TestableDesignPieData {

    @Test
    public void testHowConstructorConvertArrayToList() {
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(-i*10)); //  0 -10 -20 -30 -40
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieData pieData = new PieData(pieDataSet1);

        // It's challenging to understand the class relationships taking place in the original getYValueSum() method
        // as it couples 3 classes' methods in one line for calculating the Y-value sum
        assertEquals(-100f, pieData.getYValueSum(), 0.01f);

        // By splittig the method into two, we can test each component of the calculation method more clearly and easily

        List<PieEntry> entryList = pieData.getEntryList();
        for(int i = 0; i < entryList.size(); i++){
            assertEquals(-i*10f, entryList.get(i).getValue(), 0.01f);
        }

        assertEquals(-100f, pieData.getYValueSumV2(entryList), 0.01f);

    }
}
