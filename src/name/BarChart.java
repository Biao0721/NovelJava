package name;

import java.awt.Font;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.tabbedui.VerticalLayout;

import name.Name.MainName;

public class BarChart {
	ChartPanel jframe;
	
	private List<MainName> list1 = new ArrayList<MainName>();    //第一类
	private List<MainName> list2 = new ArrayList<MainName>();    //第二类
	private List<MainName> list3 = new ArrayList<MainName>();    //第三类
	
	public BarChart(Map<Integer, MainName> map, int x) {
		// TODO 自动生成的构造函数存根
		JTextArea taMsg = new JTextArea("分类结果:\n");
		setList(map);
		
		DefaultCategoryDataset data = (DefaultCategoryDataset) getDataSet2(map);
		JFreeChart chart = ChartFactory.createBarChart3D(
				"出现次数", 
				"章节", 
				"次数", 
				data,
				PlotOrientation.VERTICAL,
				true, false, false);
		
        CategoryPlot plot = chart.getCategoryPlot();                    		//获得图表区域对象
        CategoryAxis domain = plot.getDomainAxis();                             //水平底部列表 
        domain.setTickLabelFont(new Font("黑体", Font.BOLD, 10));               //垂直标题字体设置
        domain.setLabelFont(new Font("黑体", Font.BOLD, 10));                   //水平底部标题设置
        ValueAxis rangeAxis = plot.getRangeAxis();                             //获取柱状体
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 10));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 16));         //设置legend字体
        chart.getTitle().setFont(new Font("黑体", Font.BOLD, 16));
        
        jframe = new ChartPanel(chart);
        jframe.add(taMsg);
        jframe.setLayout(new VerticalLayout());
		Font font = new Font("黑体", 0, 20);     //设置字体格式
        taMsg.setFont(font);
        
        taMsg.append("第一类：");
        for (int i = 0; i < list1.size(); i++) {
			taMsg.append(list1.get(i).getName() + " ");
		}
        taMsg.append("\n第二类：");
        for (int i = 0; i < list2.size(); i++) {
			taMsg.append(list2.get(i).getName() + " ");
		}
        taMsg.append("\n第三类：");
        for (int i = 0; i < list3.size(); i++) {
			taMsg.append(list3.get(i).getName() + " ");
		}
        
        try {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\87776\\Desktop\\HarryPotter\\Fun4BarChart.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 1000, 700);
	        fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public BarChart(Map<Integer, MainName> map) {
		JTextArea taMsg = new JTextArea("存在感排名为：\n");
		
		DefaultCategoryDataset data = (DefaultCategoryDataset) getDataSet1(map);
		JFreeChart chart = ChartFactory.createBarChart3D(
				"人名出现次数", 
				"人名", 
				"次数", 
				data,
				PlotOrientation.VERTICAL,
				true, false, false);
		
        CategoryPlot plot = chart.getCategoryPlot();                    		//获得图表区域对象
        CategoryAxis domain = plot.getDomainAxis();                             //水平底部列表 
        domain.setTickLabelFont(new Font("黑体", Font.BOLD, 10));               //垂直标题字体设置
        domain.setLabelFont(new Font("黑体", Font.BOLD, 10));                   //水平底部标题设置
        ValueAxis rangeAxis = plot.getRangeAxis();                             //获取柱状体
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 10));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 16));         //设置legend字体
        chart.getTitle().setFont(new Font("黑体", Font.BOLD, 16));
        
        jframe = new ChartPanel(chart);
        jframe.add(taMsg);
        jframe.setLayout(new VerticalLayout());
		Font font = new Font("黑体", 0, 20);     //设置字体格式
		taMsg.setFont(font);                     //更改taMsg字体格式
        
        for (int i = 0; i < map.size(); i++) {
			taMsg.append(map.get(i).getName() + " ");
			
		}
        
        try {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\87776\\Desktop\\HarryPotter\\BarChart.jpg");
	        ChartUtilities.writeChartAsJPEG(fos, chart, 1000, 700);
	        fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ChartPanel getPanel() {
		return jframe;
	}
	
	private void setList(Map<Integer, MainName> map) {
		for (int i = 0; i < map.size(); i++) {
			double avgNum = map.get(i).getAvgNum();
			if(avgNum <= 3) {
				list1.add(map.get(i));
			}else if (avgNum >= 7.5) {
				list3.add(map.get(i));
			}else {
				list2.add(map.get(i));
			}
		}
	}
	
	private CategoryDataset getDataSet1(Map<Integer, MainName> map) {
		 DefaultCategoryDataset data=new DefaultCategoryDataset();
		 //设置数据
		 for (int i = 0; i < 10; i++) {
			data.setValue(map.get(i).getSumNum(), "", map.get(i).getName());
		}
		 return data;
	}
	
	private CategoryDataset getDataSet2(Map<Integer, MainName> map) {
		 DefaultCategoryDataset data=new DefaultCategoryDataset();
		 //设置数据
		 for (int i = 0; i < 17; i++) {
			for (int j = 0; j < map.size(); j++) {
//				data.setValue((map.get(j).getNum()[i] * 100 / map.get(j).getSumNum()), "第" + (i + 1) + "章", map.get(j).getName());
			    data.setValue(map.get(j).getAvgNum(), "", map.get(j).getName());
			}
		}
		 return data;
	}
}