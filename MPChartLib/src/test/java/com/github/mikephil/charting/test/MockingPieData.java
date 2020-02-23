package com.github.mikephil.charting.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import java.util.Arrays;
import java.util.List;
public class MockingPieData {

    private IPieDataSet iPieDataSet;
    private PieData pieData;
    private List<PieEntry> pieEntryList;
    @Spy
    private PieEntry pieEntry1 = new PieEntry(0);
    @Spy
    private PieEntry pieEntry2 = new PieEntry(-6);
    @Spy
    private PieEntry pieEntry3 = new PieEntry(8);
    @Before
    public void setup() {
        //  Mocks are being created.
        iPieDataSet = mock(IPieDataSet.class);
        pieData = new PieData(iPieDataSet);
        MockitoAnnotations.initMocks(this);
        pieEntryList = Arrays.asList(pieEntry1, pieEntry2, pieEntry3);
    }
    @Test
    public void testTimes(){
        // Test on how many times that MockingObject.getEntryForIndex() is called when calling PieData.getYValueSum() method
        when(iPieDataSet.getEntryCount()).thenReturn(pieEntryList.size());
        for(int i = 0; i < pieEntryList.size(); i++){
            when(iPieDataSet.getEntryForIndex(i)).thenReturn(pieEntryList.get(i));
        }
        assertThat(pieData.getYValueSum()).isEqualTo(2);
        verify(iPieDataSet, times(3)).getEntryForIndex(anyInt());
    }
    @Test
    public void testOrder(){
        // Test on the calling sequence of MockingObject.getEntryForIndex() when calling PieData.getYValueSum()
        when(iPieDataSet.getEntryCount()).thenReturn(pieEntryList.size());
        for(int i = 0; i < pieEntryList.size(); i++){
            when(iPieDataSet.getEntryForIndex(i)).thenReturn(pieEntryList.get(i));
        }
        assertThat(pieData.getYValueSum()).isEqualTo(2);
        InOrder inOrder = inOrder(iPieDataSet);
        inOrder.verify(iPieDataSet).getEntryForIndex(0);
        inOrder.verify(iPieDataSet).getEntryForIndex(1);
        inOrder.verify(iPieDataSet).getEntryForIndex(2);
    }
    @Test
    public void testArgumentCaptor(){
        // Test on the calling arguments of MockingObject.getEntryForIndex() when calling PieData.getYValueSum()
        when(iPieDataSet.getEntryCount()).thenReturn(pieEntryList.size());
        for(int i = 0; i < pieEntryList.size(); i++){
            when(iPieDataSet.getEntryForIndex(i)).thenReturn(pieEntryList.get(i));
        }
        assertThat(pieData.getYValueSum()).isEqualTo(2);
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(iPieDataSet, times(3)).getEntryForIndex(integerArgumentCaptor.capture());
        assertThat(integerArgumentCaptor.getAllValues()).containsExactly(0,1,2);
    }
}