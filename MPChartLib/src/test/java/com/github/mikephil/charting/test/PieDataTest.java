package com.github.mikephil.charting.test;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;

public class PieDataTest {
    @Test
    public void testPositiveInvalidIndex(){
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(i*10)); // 0 10 20 30 40
            values2.add(new PieEntry(i*3));  // 0 3 6 9 12
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(1);
        assertEquals(null, after_pieDataSet1);
    }

    @Test
    public void testNegativeInvalidIndex(){
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(i*10)); // 0 10 20 30 40
            values2.add(new PieEntry(i*3));  // 0 3 6 9 12
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(-1);
        assertEquals(null, after_pieDataSet1);
    }

    @Test
    public void testValidIndex(){
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(i*10)); // 0 10 20 30 40
            values2.add(new PieEntry(i*3));  // 0 3 6 9 12
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);
        // testing getDataSet_1.1: maximum of Y
        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(0);
        assertEquals(40f,after_pieDataSet1.getYMax(), 0.01f);
    }

    @Test
    public void testDifferentDataTypeInput(){
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry((int) i*10)); // 0 10 20 30 40 ->int
            values2.add(new PieEntry((long) i*3));  // 0 3 6 9 12 ->long
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        // testing setDataSet_1
        // testing getDataSet_1.1: maximum of Y
        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(1);
        assertEquals(null, after_pieDataSet1);
        if(after_pieDataSet1 == null) after_pieDataSet1 = pieData.getDataSetByIndex(0);
        assertEquals(40f,after_pieDataSet1.getYMax(), 0.01f);
        // testing getDataSet_1.2: minimum of Y
        assertEquals(0f,after_pieDataSet1.getYMin(), 0.01f);
        // testing getYValueSum()_1: 0+10+20+30+40
        assertEquals(100f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_1
        Entry entry1 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(40f, entry1.getY(), 0.01f);


        // testing setDataSet_2
        pieData.setDataSet(pieDataSet2);
        // testing getDataSet_2.1: maximum of Y
        IPieDataSet after_pieDataSet2 = pieData.getDataSetByLabel("Set2", true);
        assertEquals(12f,after_pieDataSet2.getYMax(), 0.01f);
        // testing getDataSet_2.2: minimum of Y
        assertEquals(0f,after_pieDataSet2.getYMin(), 0.01f);
        // testing getYValueSum()_2: 0+3+6+9+12
        assertEquals(30f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_2
        Entry entry2 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(12f, entry2.getY(), 0.01f);
    }

    @Test
    public void testPositiveInputEntries(){
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(i*10)); // 0 10 20 30 40
            values2.add(new PieEntry(i*3));  // 0 3 6 9 12
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        // testing setDataSet_1
        // testing getDataSet_1.1: maximum of Y
        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(1);
        assertEquals(null, after_pieDataSet1);
        if(after_pieDataSet1 == null) after_pieDataSet1 = pieData.getDataSetByIndex(0);
        assertEquals(40f,after_pieDataSet1.getYMax(), 0.01f);
        // testing getDataSet_1.2: minimum of Y
        assertEquals(0f,after_pieDataSet1.getYMin(), 0.01f);
        // testing getYValueSum()_1: 0+10+20+30+40
        assertEquals(100f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_1
        Entry entry1 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(40f, entry1.getY(), 0.01f);


        // testing setDataSet_2
        pieData.setDataSet(pieDataSet2);
        // testing getDataSet_2.1: maximum of Y
        IPieDataSet after_pieDataSet2 = pieData.getDataSetByLabel("Set2", true);
        assertEquals(12f,after_pieDataSet2.getYMax(), 0.01f);
        // testing getDataSet_2.2: minimum of Y
        assertEquals(0f,after_pieDataSet2.getYMin(), 0.01f);
        // testing getYValueSum()_2: 0+3+6+9+12
        assertEquals(30f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_2
        Entry entry2 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(12f, entry2.getY(), 0.01f);
    }

    @Test
    public void testAllTheSameInputEntries(){
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(2)); // 2 2 2 2 2
            values2.add(new PieEntry(1));  // 1 1 1 1 1
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        // testing setDataSet_1
        // testing getDataSet_1.1: maximum of Y
        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(1);
        assertEquals(null, after_pieDataSet1);
        if(after_pieDataSet1 == null) after_pieDataSet1 = pieData.getDataSetByIndex(0);
        assertEquals(2f,after_pieDataSet1.getYMax(), 0.01f);
        // testing getDataSet_1.2: minimum of Y
        assertEquals(2f,after_pieDataSet1.getYMin(), 0.01f);
        // testing getYValueSum()_1: 0+10+20+30+40
        assertEquals(10f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_1
        Entry entry1 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(2f, entry1.getY(), 0.01f);


        // testing setDataSet_2
        pieData.setDataSet(pieDataSet2);
        // testing getDataSet_2.1: maximum of Y
        IPieDataSet after_pieDataSet2 = pieData.getDataSetByLabel("Set2", true);
        assertEquals(1f,after_pieDataSet2.getYMax(), 0.01f);
        // testing getDataSet_2.2: minimum of Y
        assertEquals(1f,after_pieDataSet2.getYMin(), 0.01f);
        // testing getYValueSum()_2: 0+3+6+9+12
        assertEquals(5f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_2
        Entry entry2 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(1f, entry2.getY(), 0.01f);
    }

    @Test
    public void testNegativeInputEntries(){
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(-i*10)); //  0 -10 -20 -30 -40
            values2.add(new PieEntry(-i*3));  // 0 -3 -6 -9 -12
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        // testing setDataSet_1
        // testing getDataSet_1.1: maximum of Y
        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(1);
        assertEquals(null, after_pieDataSet1);
        if(after_pieDataSet1 == null) after_pieDataSet1 = pieData.getDataSetByIndex(0);
        assertEquals(0f,after_pieDataSet1.getYMax(), 0.01f);
        // testing getDataSet_1.2: minimum of Y
        assertEquals(-40f,after_pieDataSet1.getYMin(), 0.01f);
        // testing getYValueSum()_1: 0+10+20+30+40
        assertEquals(-100f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_1
        Entry entry1 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(-40f, entry1.getY(), 0.01f);


        // testing setDataSet_2
        pieData.setDataSet(pieDataSet2);
        // testing getDataSet_2.1: maximum of Y
        IPieDataSet after_pieDataSet2 = pieData.getDataSetByLabel("Set2", true);
        assertEquals(0f,after_pieDataSet2.getYMax(), 0.01f);
        // testing getDataSet_2.2: minimum of Y
        assertEquals(-12f,after_pieDataSet2.getYMin(), 0.01f);
        // testing getYValueSum()_2: 0+3+6+9+12
        assertEquals(-30f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_2
        Entry entry2 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(-12f, entry2.getY(), 0.01f);
    }

    @Test
    public void testMixedInputEntries(){
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(-i*10+20)); //  20 10 0 -10 -20
            values2.add(new PieEntry(-i*3+6));  // 6 3 0 -3 -6
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        // testing setDataSet_1
        // testing getDataSet_1.1: maximum of Y
        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(1);
        assertEquals(null, after_pieDataSet1);
        if(after_pieDataSet1 == null) after_pieDataSet1 = pieData.getDataSetByIndex(0);
        assertEquals(20f,after_pieDataSet1.getYMax(), 0.01f);
        // testing getDataSet_1.2: minimum of Y
        assertEquals(-20f,after_pieDataSet1.getYMin(), 0.01f);
        // testing getYValueSum()_1: 0+10+20+30+40
        assertEquals(0, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_1
        Entry entry1 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(-20f, entry1.getY(), 0.01f);


        // testing setDataSet_2
        pieData.setDataSet(pieDataSet2);
        // testing getDataSet_2.1: maximum of Y
        IPieDataSet after_pieDataSet2 = pieData.getDataSetByLabel("Set2", true);
        assertEquals(6f,after_pieDataSet2.getYMax(), 0.01f);
        // testing getDataSet_2.2: minimum of Y
        assertEquals(-6f,after_pieDataSet2.getYMin(), 0.01f);
        // testing getYValueSum()_2: 0+3+6+9+12
        assertEquals(0f, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_2
        Entry entry2 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(-6f, entry2.getY(), 0.01f);

    }

    @Test
    public void testBoundaryInputEntries(){
        // float.MAX_VALUE
        // float.MIN_VALUE
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(Float.MAX_VALUE)); //  Float.MAX_VALUE * 5
            values2.add(new PieEntry(Float.MIN_VALUE));  // Float.MIN_VALUE * 5
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        // testing setDataSet_1
        // testing getDataSet_1.1: maximum of Y
        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(1);
        assertEquals(null, after_pieDataSet1);
        if(after_pieDataSet1 == null) after_pieDataSet1 = pieData.getDataSetByIndex(0);
        assertEquals(Float.MAX_VALUE,after_pieDataSet1.getYMax(), 0.01f);
        // testing getDataSet_1.2: minimum of Y
        assertEquals(Float.MAX_VALUE,after_pieDataSet1.getYMin(), 0.01f);
        // testing getYValueSum()_1: 0+10+20+30+40
        assertEquals(Float.MAX_VALUE, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_1
        Entry entry1 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(Float.MAX_VALUE, entry1.getY(), 0.01f);


        // testing setDataSet_2
        pieData.setDataSet(pieDataSet2);
        // testing getDataSet_2.1: maximum of Y
        IPieDataSet after_pieDataSet2 = pieData.getDataSetByLabel("Set2", true);
        assertEquals(Float.MIN_VALUE,after_pieDataSet2.getYMax(), 0.01f);
        // testing getDataSet_2.2: minimum of Y
        assertEquals(Float.MIN_VALUE,after_pieDataSet2.getYMin(), 0.01f);
        // testing getYValueSum()_2: 0+3+6+9+12
        assertEquals(Float.MIN_VALUE, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_2
        Entry entry2 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(Float.MIN_VALUE, entry2.getY(), 0.01f);
    }

    @Test
    public void testAllZeroInputEntries(){
        // float.MAX_VALUE
        // float.MIN_VALUE
        // create a list of PieEntry
        List<PieEntry> values1 = new ArrayList<>();
        List<PieEntry> values2 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            values1.add(new PieEntry(0)); //  0 * 5
            values2.add(new PieEntry(0));  // 0 * 5
        }

        PieDataSet pieDataSet1 = new PieDataSet(values1, "Set1");
        PieDataSet pieDataSet2 = new PieDataSet(values2, "Set2");
        PieData pieData = new PieData(pieDataSet1);

        // testing setDataSet_1
        // testing getDataSet_1.1: maximum of Y
        IPieDataSet after_pieDataSet1 = pieData.getDataSetByIndex(1);
        assertEquals(null, after_pieDataSet1);
        if(after_pieDataSet1 == null) after_pieDataSet1 = pieData.getDataSetByIndex(0);
        assertEquals(0,after_pieDataSet1.getYMax(), 0.01f);
        // testing getDataSet_1.2: minimum of Y
        assertEquals(0,after_pieDataSet1.getYMin(), 0.01f);
        // testing getYValueSum()_1: 0+10+20+30+40
        assertEquals(0, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_1
        Entry entry1 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(0, entry1.getY(), 0.01f);


        // testing setDataSet_2
        pieData.setDataSet(pieDataSet2);
        // testing getDataSet_2.1: maximum of Y
        IPieDataSet after_pieDataSet2 = pieData.getDataSetByLabel("Set2", true);
        assertEquals(0,after_pieDataSet2.getYMax(), 0.01f);
        // testing getDataSet_2.2: minimum of Y
        assertEquals(0,after_pieDataSet2.getYMin(), 0.01f);
        // testing getYValueSum()_2: 0+3+6+9+12
        assertEquals(0, pieData.getYValueSum(), 0.01f);
        // testing getEntryForHighlight()_2
        Entry entry2 = pieData.getEntryForHighlight(new Highlight(4,0,0));
        assertEquals(0, entry2.getY(), 0.01f);
    }
}
