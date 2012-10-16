package SiteView.ecc.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import SiteView.ecc.reportchart.EccReportChart;
import SiteView.ecc.tab.views.MonitorLogTabView;
import SiteView.ecc.tab.views.TotalTabView;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

/**
 * 趋势报告视图
 * 
 * @author Administrator
 * 
 */
public class TrendReportView extends ViewPart {
	public Composite trendComposite;
	public String startTimeStr = "";
	public String endTimeStr = "";
	private Button queryBtn;
	private Label start;
	private Label queryLabel;

	public TrendReportView() {

	}

	@Override
	public void createPartControl(final Composite parent) {
		// TODO Auto-generated method stub
		refreshComposite(parent);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * refresh Composite
	 */
	public void refreshComposite(final Composite trendComposite) {
		if (trendComposite.getChildren().length > 0) {
			for (Control control : trendComposite.getChildren()) {
				control.dispose();
			}
		}
		TotalTabView.startTime = startTimeStr;
		TotalTabView.endTime = endTimeStr;
		if (TotalTabView.businessObj != null) {
			TotalTabView.setTotalData(TotalTabView.businessObj);
		}
		trendComposite.setLayout(new FillLayout());
		trendComposite.setBackground(SiteView.ecc.view.EccTreeControl.color);
		SashForm reportForm = new SashForm(trendComposite, SWT.BORDER);
		reportForm.setOrientation(SWT.VERTICAL);
		reportForm.setLayout(new FillLayout());

		Composite queryComposite = new Composite(reportForm, SWT.NONE);
		queryComposite.setBackground(SiteView.ecc.view.EccTreeControl.color);
		queryComposite.setLayout(new FormLayout());

		queryLabel = new Label(queryComposite, SWT.NONE);
		FormData fd_queryLabel = new FormData();
		fd_queryLabel.left = new FormAttachment(0);
		fd_queryLabel.right = new FormAttachment(100);
		fd_queryLabel.top = new FormAttachment(0);
		queryLabel.setLayoutData(fd_queryLabel);
		queryLabel.setText("查询条件");
		queryLabel.setBackground(new Color(null, 191, 198, 216));

		start = new Label(queryComposite, SWT.None);
		FormData fd_start = new FormData();
		fd_start.left = new FormAttachment(0);
		start.setBackground(SiteView.ecc.view.EccTreeControl.color);
		start.setLayoutData(fd_start);
		start.setText("开始时间:");
		final DateTime startDate = new DateTime(queryComposite, SWT.DROP_DOWN);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.US);
		Date startDateTime = new Date();
		Date endDateTime = new Date();
		try {
			if (startTimeStr.equals("") || endTimeStr.equals("")) {
				String time = MonitorLogTabView.getHoursAgoTime(2);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
			}
			startDateTime = sdf.parse(startTimeStr);
			endDateTime = sdf.parse(endTimeStr);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Calendar startcal = Calendar.getInstance();
		startcal.setTime(startDateTime);
		startDate.setYear(startcal.get(Calendar.YEAR));
		startDate.setMonth(startcal.get(Calendar.MONTH));
		startDate.setDay(startcal.get(Calendar.DAY_OF_MONTH));
		FormData fd_startDate = new FormData();
		fd_startDate.left = new FormAttachment(start);
		startDate.setLayoutData(fd_startDate);
		final DateTime startTime = new DateTime(queryComposite, SWT.TIME
				| SWT.SHORT);
		FormData fd_startTime = new FormData();
		fd_startTime.left = new FormAttachment(startDate, 3);
		startTime.setLayoutData(fd_startTime);
		startTime.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime.setMinutes(startcal.get(Calendar.MINUTE));
		startTime.setSeconds(startcal.get(Calendar.SECOND));
		Label end = new Label(queryComposite, SWT.None);
		end.setBackground(SiteView.ecc.view.EccTreeControl.color);
		FormData fd_end = new FormData();
		end.setLayoutData(fd_end);
		end.setText("结束时间:");
		final DateTime endDate = new DateTime(queryComposite, SWT.DROP_DOWN);
		fd_end.right = new FormAttachment(100, -331);
		Calendar endcal = Calendar.getInstance();
		endcal.setTime(endDateTime);
		endDate.setYear(endcal.get(Calendar.YEAR));
		endDate.setMonth(endcal.get(Calendar.MONTH));
		endDate.setDay(endcal.get(Calendar.DAY_OF_MONTH));
		FormData fd_endDate = new FormData();
		fd_endDate.left = new FormAttachment(end, 6);
		fd_endDate.top = new FormAttachment(start, 0, SWT.TOP);
		fd_endDate.bottom = new FormAttachment(start, 0, SWT.BOTTOM);
		endDate.setLayoutData(fd_endDate);
		final DateTime endTime = new DateTime(queryComposite, SWT.TIME
				| SWT.SHORT);
		fd_endDate.right = new FormAttachment(100, -247);
		endTime.setHours(endcal.get(Calendar.HOUR_OF_DAY));
		endTime.setMinutes(endcal.get(Calendar.MINUTE));
		endTime.setSeconds(endcal.get(Calendar.SECOND));
		FormData fd_endTime = new FormData();
		fd_endTime.bottom = new FormAttachment(start, 0, SWT.BOTTOM);
		fd_endTime.left = new FormAttachment(endDate, 6);
		endTime.setLayoutData(fd_endTime);
		queryBtn = new Button(queryComposite, SWT.NONE);
		FormData fd_queryBtn = new FormData();
		fd_queryBtn.top = new FormAttachment(start, -5, SWT.TOP);
		fd_queryBtn.left = new FormAttachment(endTime, 6);
		queryBtn.setLayoutData(fd_queryBtn);
		queryBtn.setText("查询");
		queryBtn.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr = startDate.getYear() + "-"
						+ (startDate.getMonth() + 1) + "-" + startDate.getDay()
						+ " " + startTime.getHours() + ":"
						+ startTime.getMinutes() + ":" + startTime.getSeconds();
				endTimeStr = endDate.getYear() + "-" + (endDate.getMonth() + 1)
						+ "-" + endDate.getDay() + " " + endTime.getHours()
						+ ":" + endTime.getMinutes() + ":"
						+ endTime.getSeconds();
				refreshComposite(trendComposite);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Label towHourBtn = new Label(queryComposite, SWT.NONE);
		fd_start.top = new FormAttachment(towHourBtn, 12);
		towHourBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(queryLabel, 3);
		fd_lblNewLabel.left = new FormAttachment(queryLabel, 10, SWT.LEFT);
		towHourBtn.setLayoutData(fd_lblNewLabel);
		towHourBtn.setText("2小时");
		towHourBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				String time = MonitorLogTabView.getHoursAgoTime(2);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Label fourHourBtn = new Label(queryComposite, SWT.NONE);
		fd_startDate.top = new FormAttachment(fourHourBtn, 10);
		fourHourBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
		fd_lblNewLabel_1.left = new FormAttachment(towHourBtn, 16);
		fourHourBtn.setLayoutData(fd_lblNewLabel_1);
		fourHourBtn.setText("4小时");
		fourHourBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				String time = MonitorLogTabView.getHoursAgoTime(4);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Label eightHourBtn = new Label(queryComposite, SWT.NONE);
		eightHourBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
		fd_lblNewLabel_2.left = new FormAttachment(fourHourBtn, 17);
		eightHourBtn.setLayoutData(fd_lblNewLabel_2);
		eightHourBtn.setText("8小时");
		eightHourBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				String time = MonitorLogTabView.getHoursAgoTime(8);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Label runningLabel = new Label(queryComposite, SWT.NONE);
		fd_end.bottom = new FormAttachment(runningLabel, -11);
		fd_start.bottom = new FormAttachment(runningLabel, -8);
		FormData fd_runningLabel = new FormData();
		fd_runningLabel.left = new FormAttachment(queryLabel, 0, SWT.LEFT);
		fd_runningLabel.right = new FormAttachment(100);
		runningLabel.setLayoutData(fd_runningLabel);
		runningLabel.setText("运行情况表");
		runningLabel.setBackground(new Color(null, 191, 198, 216));

		TableViewer tableViewer = new TableViewer(queryComposite, SWT.MULTI
				| SWT.FULL_SELECTION | SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL);
		Table table = tableViewer.getTable();
		fd_runningLabel.bottom = new FormAttachment(table);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 75);
		//fd_table.left = new FormAttachment(0);
		table.setLayoutData(fd_table);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBackground(SiteView.ecc.view.EccTreeControl.color);
		TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
		newColumnTableColumn.setWidth(103);
		newColumnTableColumn.setText("名称");
		TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_1.setWidth(114);
		newColumnTableColumn_1.setText("正常运行时间(%)");
		TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_2.setWidth(110);
		newColumnTableColumn_2.setText("危险(%)");
		TableColumn newColumnTableColumn_3 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_3.setWidth(110);
		newColumnTableColumn_3.setText("错误(%)");
		TableColumn newColumnTableColumn_4 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_4.setWidth(110);
		newColumnTableColumn_4.setText("最新");
		TableColumn newColumnTableColumn_5 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_5.setWidth(520);
		newColumnTableColumn_5.setText("阀值");
		if(TotalTabView.businessObj !=null){
			TableItem runningitem = new TableItem(table, SWT.NONE);
			String runningstr = TotalTabView.monitorName + "&"
					+ TotalTabView.goodPercentOf + "&"
					+ TotalTabView.warningPercentOf + "&"
					+ TotalTabView.errorPercentOf + "&" + TotalTabView.laststatus
					+ "&错误阀值：" + TotalTabView.errorAlarmCondition + " 危险阀值："
					+ TotalTabView.warningAlarmCondition + " 正常阀值："
					+ TotalTabView.goodAlarmCondition;
			String[] runningstrdata = runningstr.split("&");
			runningitem.setText(runningstrdata);
		}
		Label monitorTotalReportLabel = new Label(queryComposite, SWT.NONE);
		fd_table.bottom = new FormAttachment(monitorTotalReportLabel, -26);
		FormData fd_monitorTotalReportLabel = new FormData();
		fd_monitorTotalReportLabel.left = new FormAttachment(queryLabel, 0, SWT.LEFT);
		fd_monitorTotalReportLabel.right = new FormAttachment(queryLabel, 0, SWT.RIGHT);
		monitorTotalReportLabel.setLayoutData(fd_monitorTotalReportLabel);
		monitorTotalReportLabel.setText("监测器统计报表");
		monitorTotalReportLabel.setBackground(new Color(null, 191, 198, 216));
		fd_monitorTotalReportLabel.bottom = new FormAttachment(100, -73);
		String[] headers = { "名称", "返回值名称", "最大值", "最小值", "平均值", "最近一次",
				"最大值时间" };

		Label oneDayBtn = new Label(queryComposite, SWT.NONE);
		fd_startTime.top = new FormAttachment(oneDayBtn, 9);
		FormData fd_oneDayBtn = new FormData();
		fd_oneDayBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
		fd_oneDayBtn.left = new FormAttachment(eightHourBtn, 18);
		oneDayBtn.setLayoutData(fd_oneDayBtn);
		oneDayBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		oneDayBtn.setText("1天");

		Label threeDayBtn = new Label(queryComposite, SWT.NONE);
		FormData fd_threeDayBtn = new FormData();
		fd_threeDayBtn.left = new FormAttachment(oneDayBtn, 51);
		fd_threeDayBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
		threeDayBtn.setLayoutData(fd_threeDayBtn);
		threeDayBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		threeDayBtn.setText("3天");
		Label fiveDayBtn = new Label(queryComposite, SWT.NONE);
		FormData fd_fiveDayBtn = new FormData();
		fd_fiveDayBtn.left = new FormAttachment(threeDayBtn, 22);
		fd_fiveDayBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
		fiveDayBtn.setLayoutData(fd_fiveDayBtn);
		fiveDayBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		fiveDayBtn.setText("5天");

		Label thisWeekBtn = new Label(queryComposite, SWT.NONE);
		FormData fd_thisWeekBtn = new FormData();
		fd_thisWeekBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
		fd_thisWeekBtn.left = new FormAttachment(fiveDayBtn, 16);
		thisWeekBtn.setLayoutData(fd_thisWeekBtn);
		thisWeekBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		thisWeekBtn.setText("本周");

		Label oneWeekBtn = new Label(queryComposite, SWT.NONE);
		FormData fd_oneWeekBtn = new FormData();
		fd_oneWeekBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
		fd_oneWeekBtn.left = new FormAttachment(thisWeekBtn, 21);
		oneWeekBtn.setLayoutData(fd_oneWeekBtn);
		oneWeekBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		oneWeekBtn.setText("1周");

		Label twoDayBtn = new Label(queryComposite, SWT.NONE);
		FormData fd_twoDayBtn = new FormData();
		fd_twoDayBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
		fd_twoDayBtn.left = new FormAttachment(oneDayBtn, 16);
		twoDayBtn.setLayoutData(fd_twoDayBtn);
		twoDayBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
		twoDayBtn.setText("2天");
								
										TableViewer totaltableViewer = new TableViewer(queryComposite,
												SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL
														| SWT.H_SCROLL);
										Table totaltable = totaltableViewer.getTable();
										FormData fd_totaltable = new FormData();
										fd_totaltable.top = new FormAttachment(monitorTotalReportLabel, 1);
										totaltable.setLayoutData(fd_totaltable);
										totaltable.setLinesVisible(true);
										totaltable.setHeaderVisible(true);
										totaltable.setBackground(SiteView.ecc.view.EccTreeControl.color);
										TableColumn totalColumnTableColumn = new TableColumn(totaltable,
												SWT.NONE);
										totalColumnTableColumn.setWidth(81);
										totalColumnTableColumn.setText(headers[0]);
										
												TableColumn totalColumnTableColumn_1 = new TableColumn(totaltable,
														SWT.NONE);
												totalColumnTableColumn_1.setWidth(93);
												totalColumnTableColumn_1.setText(headers[1]);
												TableColumn totalColumnTableColumn_2 = new TableColumn(totaltable,
														SWT.NONE);
												totalColumnTableColumn_2.setWidth(92);
												totalColumnTableColumn_2.setText(headers[2]);
												TableColumn totalColumnTableColumn_3 = new TableColumn(totaltable,
														SWT.NONE);
												totalColumnTableColumn_3.setWidth(107);
												totalColumnTableColumn_3.setText(headers[3]);
												TableColumn totalColumnTableColumn_4 = new TableColumn(totaltable,
														SWT.NONE);
												totalColumnTableColumn_4.setWidth(101);
												totalColumnTableColumn_4.setText(headers[4]);
												TableColumn totalColumnTableColumn_5 = new TableColumn(totaltable,
														SWT.NONE);
												totalColumnTableColumn_5.setWidth(75);
												totalColumnTableColumn_5.setText(headers[5]);
												TableColumn totalColumnTableColumn_6 = new TableColumn(totaltable,
														SWT.NONE);
												totalColumnTableColumn_6.setWidth(412);
												totalColumnTableColumn_6.setText(headers[6]);
												
														Label oneMonthBtn = new Label(queryComposite, SWT.NONE);
														FormData fd_oneMonthBtn = new FormData();
														fd_oneMonthBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
														fd_oneMonthBtn.right = new FormAttachment(endTime, 0, SWT.RIGHT);
														oneMonthBtn.setLayoutData(fd_oneMonthBtn);
														oneMonthBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
														oneMonthBtn.setText("1个月");
														
																Label twoMonthBtn = new Label(queryComposite, SWT.NONE);
																FormData fd_twoMonthBtn = new FormData();
																fd_twoMonthBtn.bottom = new FormAttachment(queryBtn, -6);
																fd_twoMonthBtn.right = new FormAttachment(queryBtn, 0, SWT.RIGHT);
																twoMonthBtn.setLayoutData(fd_twoMonthBtn);
																twoMonthBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
																twoMonthBtn.setText("2个月");
																
																		Label sixMonthBtn = new Label(queryComposite, SWT.NONE);
																		FormData fd_sixMonthBtn = new FormData();
																		fd_sixMonthBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
																		fd_sixMonthBtn.left = new FormAttachment(twoMonthBtn, 15);
																		sixMonthBtn.setLayoutData(fd_sixMonthBtn);
																		sixMonthBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
																		sixMonthBtn.setText("6个月");
																		
																				Label thisDayBtn = new Label(queryComposite, SWT.NONE);
																				FormData fd_thisDayBtn = new FormData();
																				fd_thisDayBtn.top = new FormAttachment(towHourBtn, 0, SWT.TOP);
																				fd_thisDayBtn.left = new FormAttachment(sixMonthBtn, 17);
																				thisDayBtn.setLayoutData(fd_thisDayBtn);
																				thisDayBtn.setBackground(SiteView.ecc.view.EccTreeControl.color);
																				thisDayBtn.setText("当天");
																				thisDayBtn.addMouseListener(new MouseListener() {

																					@Override
																					public void mouseUp(MouseEvent e) {
																						// TODO Auto-generated method stub

																					}

																					@Override
																					public void mouseDown(MouseEvent e) {
																						// TODO Auto-generated method stub
																						Calendar todayStart = Calendar.getInstance();
																						todayStart.set(Calendar.HOUR, 0);
																						todayStart.set(Calendar.MINUTE, 0);
																						todayStart.set(Calendar.SECOND, 0);
																						todayStart.set(Calendar.MILLISECOND, 0);
																						startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
																								.format(todayStart.getTime());
																						Calendar todayEnd = Calendar.getInstance();
																						todayEnd.set(Calendar.HOUR, 23);
																						todayEnd.set(Calendar.MINUTE, 59);
																						todayEnd.set(Calendar.SECOND, 59);
																						todayEnd.set(Calendar.MILLISECOND, 999);
																						endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
																								.format(todayEnd.getTime());
																						refreshComposite(trendComposite);
																					}

																					@Override
																					public void mouseDoubleClick(MouseEvent e) {
																						// TODO Auto-generated method stub

																					}
																				});
																		sixMonthBtn.addMouseListener(new MouseListener() {

																			@Override
																			public void mouseUp(MouseEvent e) {
																				// TODO Auto-generated method stub

																			}

																			@Override
																			public void mouseDown(MouseEvent e) {
																				// TODO Auto-generated method stub
																				Calendar cal = Calendar.getInstance();
																				endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
																						.format(cal.getTime());
																				cal.add(cal.MONTH, -6);
																				startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
																						.format(cal.getTime());
																				refreshComposite(trendComposite);
																			}

																			@Override
																			public void mouseDoubleClick(MouseEvent e) {
																				// TODO Auto-generated method stub

																			}
																		});
																twoMonthBtn.addMouseListener(new MouseListener() {

																	@Override
																	public void mouseUp(MouseEvent e) {
																		// TODO Auto-generated method stub

																	}

																	@Override
																	public void mouseDown(MouseEvent e) {
																		// TODO Auto-generated method stub
																		Calendar cal = Calendar.getInstance();
																		endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
																				.format(cal.getTime());
																		cal.add(cal.MONTH, -2);
																		startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
																				.format(cal.getTime());
																		refreshComposite(trendComposite);
																	}

																	@Override
																	public void mouseDoubleClick(MouseEvent e) {
																		// TODO Auto-generated method stub

																	}
																});
														oneMonthBtn.addMouseListener(new MouseListener() {

															@Override
															public void mouseUp(MouseEvent e) {
																// TODO Auto-generated method stub

															}

															@Override
															public void mouseDown(MouseEvent e) {
																// TODO Auto-generated method stub
																Calendar cal = Calendar.getInstance();
																endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
																		.format(cal.getTime());
																cal.add(cal.MONTH, -1);
																startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
																		.format(cal.getTime());
																refreshComposite(trendComposite);
															}

															@Override
															public void mouseDoubleClick(MouseEvent e) {
																// TODO Auto-generated method stub

															}
														});
		twoDayBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				String time = MonitorLogTabView.getHoursAgoTime(48);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		oneWeekBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				String time = MonitorLogTabView.getHoursAgoTime(168);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		thisWeekBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				Calendar cal = Calendar.getInstance();
				int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
				cal.add(Calendar.DATE, -day_of_week);
				startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(cal.getTime());
				cal.add(Calendar.DATE, 6);
				endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(cal.getTime());
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		fiveDayBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				String time = MonitorLogTabView.getHoursAgoTime(120);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		threeDayBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				String time = MonitorLogTabView.getHoursAgoTime(72);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		oneDayBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				String time = MonitorLogTabView.getHoursAgoTime(24);
				startTimeStr = time.substring(time.indexOf("*") + 1);
				endTimeStr = time.substring(0, time.indexOf("*"));
				refreshComposite(trendComposite);
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		if(TotalTabView.businessObj != null){
			for (Map<String, List<String>> map : TotalTabView.reportDescList) {
				Set<Map.Entry<String, List<String>>> set = map.entrySet();
				for (Iterator<Map.Entry<String, List<String>>> it = set.iterator(); it
						.hasNext();) {
					TableItem item = new TableItem(totaltable, SWT.NONE);
					Map.Entry<String, List<String>> entry = (Map.Entry<String, List<String>>) it
							.next();
					List<String> arrlist = entry.getValue();
					if (arrlist.size() > 0) {
						String str = TotalTabView.monitorName + "&"
								+ entry.getKey() + "&" + arrlist.get(0) + "&"
								+ arrlist.get(3) + "&" + arrlist.get(1) + "&"
								+ arrlist.get(2) + "&" + arrlist.get(4);
						String[] strdata = str.split("&");
						item.setText(strdata);
					}
				}
			}
		}
		ScrolledComposite scrolledComposite = new ScrolledComposite(reportForm,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(400);
		scrolledComposite.setMinHeight(300);

		Composite chatComposite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(chatComposite);// 设置chatComposite被scrolledComposite控制
		chatComposite.setBackground(SiteView.ecc.view.EccTreeControl.color);
		chatComposite.setLayout(new FormLayout());

		Label reportImgLabel = new Label(chatComposite, SWT.NONE);
		FormData fd_reportImgLabel = new FormData();
		fd_reportImgLabel.left = new FormAttachment(0);
		fd_reportImgLabel.right = new FormAttachment(100);
		reportImgLabel.setLayoutData(fd_reportImgLabel);
		reportImgLabel.setText("图表");
		reportImgLabel.setBackground(new Color(null, 191, 198, 216));
		if(TotalTabView.businessObj != null){
			 XYDataset dataset = EccReportChart
					 .createDataset(TotalTabView.xyDataArrayList);
					 JFreeChart chart = EccReportChart.createChart(dataset,
					 TotalTabView.xname, TotalTabView.yname);
					 ChartComposite frame = new ChartComposite(chatComposite, SWT.NONE,
					 chart, true);
					 FormData fd_frame = new FormData();
					 fd_frame.top = new FormAttachment(reportImgLabel, 9);
					 fd_frame.left = new FormAttachment(0);
					 fd_frame.bottom = new FormAttachment(0, 243);
					 fd_frame.right = new FormAttachment(100);
					 frame.setLayoutData(fd_frame);
		}
		 reportForm.setWeights(new int[] {218, 240});
		
		trendComposite.layout();
	}

}
