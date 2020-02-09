package name;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import name.Name.MainName;

public class PieChart {
	ChartPanel jframe;
	
	public PieChart(MainName mn) {
		DefaultPieDataset data = (DefaultPieDataset) getPieDataset(mn);
		JFreeChart chart = ChartFactory.createPieChart3D(
				mn.getName() + "各章节出现密度", data,
				true, false, false);
		chart.setAntiAlias(false);   //关闭锯齿形
			
		Font font = new Font("黑体", Font.PLAIN, 14);
		
		PiePlot plot = (PiePlot)chart.getPlot();
		//解决图像不能显示中文
		plot.setLabelFont(font);
		LegendTitle lt = chart.getLegend();
		lt.setItemFont(font);
		
		//按照出现频率大小来设置颜色深浅
		setColor(plot, data, mn);
		
		//设置字体
		TextTitle chartTitle = chart.getTitle();
		chartTitle.setFont(new Font(
				"黑体", Font.BOLD, 20));
		
		//设置百分比
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}次"));
		
		jframe = new ChartPanel(chart);
		
		//下载饼状图
		try {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\87776\\Desktop\\HarryPotter\\PieChart.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 1000, 700);
	        fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ChartPanel getPanel() {
		return jframe;
	}
	
	private void setColor(PiePlot plot, PieDataset data, MainName mn) {
		List keys = data.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			plot.setSectionPaint(keys.get(i).toString(),new Color(
					0,  mn.getNum()[i] * 1000 / mn.getSumNum() / 4, mn.getNum()[i] * 1000 / mn.getSumNum() / 4));
		}
	}

	private PieDataset getPieDataset(MainName mn){
        DefaultPieDataset dataset = new DefaultPieDataset();
        int[] num = mn.getNum();
        
        for (int i = 0; i < num.length; i++) {
			dataset.setValue("第" + (i + 1) + "章", num[i]);
		}
        return dataset;     
    }
}
	