package com.shc.msp.ft.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.data.TestData;
import com.shc.msp.ft.util.MysqlDBConnection;
import com.vmware.vim25.VirtualMachineCapability;
import com.vmware.vim25.VirtualMachineConfigInfo;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class RCTest extends BaseTestsEx {
	ArrayList<String> rcList = new ArrayList<String>();
	public boolean fetchVmsFromOHMDB = true;
	private VirtualMachine vm;
	private VirtualMachineConfigInfo vminfo;
	private VirtualMachineCapability vmc;
	private ServiceInstance si;

	@DataProvider(name = "getRCs", parallel = true)
	public Object[][] getRCs() throws Exception {
		fetchRCsFromDB();
		try {
			new File("./target/archive/failedRCs.txt").delete();
		} catch (Exception e) {}
		try {
			new File("./target/archive/RCTestResult.txt").delete();
		} catch (Exception e) {}
		
		
		String[][] dataArray = new String[rcList.size()][1];
		for (int i = 0; i < rcList.size(); i++) {
			String rc = rcList.get(i);
			dataArray[i][0] = rc;
		}
		return dataArray;
	}

	public void fetchRCsFromDB() throws SQLException, FileNotFoundException, IOException {
		if (fetchVmsFromOHMDB) {
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			conn = MysqlDBConnection.getmysqlConnectionOHMConfig();
			String sql_chrome_vms = "select host from vm v where v.id in (select rc.rc_vm_id from rc_node_config rc where rc.node_config_id in " + "(select id from node_config n where n.browser_config_id in " + "(select id from browser_config b where b.browser_name like '%chrome%'  and n.deleted=0) and n.deleted=0)) and v.is_allocated=1 and v.deleted=0";
			st = conn.prepareStatement(sql_chrome_vms);
			st.execute();
			rs = st.getResultSet();
			while (rs.next()) {
				rcList.add(rs.getString(1));
			}
		} else {
			BufferedReader f = new BufferedReader(new FileReader("RCList.txt"));
			String line = f.readLine();
			while (line != null) {
				line = line.trim();
				if (line.length() > 10 && !line.contains("#")) {
					rcList.add(line);
				}
				line = f.readLine();
			}
			f.close();
		}
	}

	@Test(dataProvider = "getRCs", groups = { "RCTest" }, description = "Verify RCs are up")
	public void VerifyRCs(String rc) throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>(rc, rc, 1);
		addCloneIDHostname(data);

		DesiredCapabilities capability = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		capability.setCapability(ChromeOptions.CAPABILITY, options);
		try {
			new File("checkedRCs.txt").delete();
		} catch (Exception e1) {}
		String hubUrlString = "http://" + rc + ":5555" + "/wd/hub";
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(hubUrlString), capability);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			driver.get("http://www.google.com/");
			driver.findElement(By.xpath("//input[@name='q']")).isDisplayed();
			driver.quit();
			checkedRc(rc);
			forceCheckDisk(rc);
		} catch (Exception e) {
			checkedRc(rc);
			updateReport(rc, e);
			resetVM(rc);
			if (driver != null) {
				driver.quit();
			}
			throw e;
		}

	}

	
	@DataProvider(name = "getThreads", parallel = true)
	public Object[][] getThreads() throws Exception {
		try {
			new File("./target/archive/failedRCs.txt").delete();
		} catch (Exception e) {}
		try {
			new File("./target/archive/RCTestResult.txt").delete();
		} catch (Exception e) {}
		
		int load = Integer.parseInt(FrameworkProperties.getProperty("thread.count", "1000"));
		String[][] dataArray = new String[load][1];
		for (int i = 0; i < load; i++) {
			String rc = "load"+i;
			dataArray[i][0] = rc;
		}
		return dataArray;
	}

	@Test(dataProvider = "getThreads", groups = { "testHub" }, description = "Test Hub with Load")
	public void testHub(String rc) throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>(rc, rc, 1);
		addCloneIDHostname(data);

		DesiredCapabilities capability = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		capability.setCapability(ChromeOptions.CAPABILITY, options);
		//String hubUrlString = "http://"+FrameworkProperties.SELENIUM_HOST+":"+FrameworkProperties.SELENIUM_PORT+"/wd/hub";
		String hubUrlString = "http://atubrc3029p.prod.ch3.s.com:5555/wd/hub";
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(hubUrlString), capability);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			driver.get("http://www.google.com/");
			WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
			if(!element.isDisplayed()){
				element = driver.findElement(By.xpath("//input[@name='captcha']"));
			}
			element.sendKeys(rc);
			Thread.sleep(1000);
			driver.quit();
			
		} catch (Exception e) {
			updateReport(rc, e);
			if (driver != null) {
				driver.quit();
			}
			throw e;
		}

	}
	@Test(dataProvider = "getThreads", groups = { "testHub1" }, description = "Test Hub with Load")
	public void testHub1(String rc) throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>(rc, rc, 1);
		addCloneIDHostname(data);

		DesiredCapabilities capability = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		capability.setCapability(ChromeOptions.CAPABILITY, options);
		String hubUrlString = "http://"+FrameworkProperties.SELENIUM_HOST+":"+FrameworkProperties.SELENIUM_PORT+"/wd/hub";
		System.out.println("Huburlstring:"+hubUrlString);
		//String hubUrlString = "http://atubh3029p.prod.ch3.s.com:4444/wd/hub";
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(hubUrlString), capability);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			driver.get("http://www.google.com/");
			WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
			if(!element.isDisplayed()){
				element = driver.findElement(By.xpath("//input[@name='captcha']"));
			}
			element.sendKeys(rc);
			Thread.sleep(1000);
			driver.quit();
			
		} catch (Exception e) {
			updateReport(rc, e);
			if (driver != null) {
				driver.quit();
			}
			throw e;
		}

	}
	
	@Test(dataProvider = "getRCs", groups = { "renameRCs" }, description = "Rename RCs")
	public void renameRCs(String rc) throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>(rc, rc, 1);
		addCloneIDHostname(data);

		try {
			JSch jsch=new JSch();
			Session session=jsch.getSession("jenkins", rc, 22);
			session.setPassword("jenkins");
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect(30000); 
			
			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand("echo "+rc+">hostname\nsudo cp hostname /etc\nsudo shutdown -r now\n");
			channel.connect();
			System.out.println("Renamed Rc:"+rc);
		} catch (Exception e) {
			System.out.println("JSH error on RC:"+rc+":-"+e.getMessage());
		}

	}

	private void forceCheckDisk(String rc) {
		try {
			JSch jsch=new JSch();
			Session session=jsch.getSession("jenkins", rc, 22);
			session.setPassword("jenkins");
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect(30000); 
			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand("sudo touch /forcefsck");
			channel.connect();
			channel.disconnect();
		    session.disconnect();
		} catch (Exception e) {
			System.out.println("JSH error on RC:"+rc+":-"+e.getMessage());
		}

	}

	@Test(groups = { "RCTest1","resetUnCheckedRCs" }, /*dependsOnMethods = { "VerifyRCs" },*/ alwaysRun = true, description = "Reset Unchecked RCs")
	public void resetUnCheckedRCs() {
		BufferedReader f;
		String line;
		try {
			File file = new File("checkedRCs.txt");
			if (file.exists()) {
				if (rcList.size() < 1) {
					fetchRCsFromDB();
				}
				f = new BufferedReader(new FileReader(file));
				line = f.readLine();
				while (line != null) {
					line = line.trim();
					if (line.length() > 10 && !line.contains("#")) {
						rcList.remove(line);
					}
					line = f.readLine();
				}
				f.close();
				new File("checkedRCs.txt").delete();

				for (String rc : rcList) {
					resetVM(rc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void checkedRc(String rc) {
		try {
			BufferedWriter fr = new BufferedWriter(new FileWriter("checkedRCs.txt", true));
			fr.write(rc + "\n");
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void resetVM(String rc) {
		try {
			if (si == null) {
				si = new ServiceInstance(new URL("https://vcenterdb301p.prod.ch3.s.com/sdk"), "kmart\\msonar0", System.getenv("VPassword"), true);
			}
			vm = (VirtualMachine) new InventoryNavigator(si.getRootFolder()).searchManagedEntity("VirtualMachine", rc);
			vminfo = vm.getConfig();
			vmc = vm.getCapability();
			vm.getResourcePool();
			System.out.println("Restarting VM :-  " + vm.getName());
			System.out.println("GuestOS: " + vminfo.getGuestFullName());
			System.out.println("Multiple snapshot supported: " + vmc.isMultipleSnapshotsSupported());
			System.out.println("**************************************************************");
			vm.resetVM_Task();
		} catch (Exception e) {
			System.out.println("Unexpected error occured while resetting the RC - " + rc + ":" + e.getMessage());
		}
	}

	public synchronized void updateReport(String rc, Exception e) throws Exception {
		new File("./target/archive/").mkdirs();
		BufferedWriter fr = new BufferedWriter(new FileWriter("./target/archive/RCTestResult.txt", true));
		fr.write(rc + ":" + e.getMessage() + "\n\n");
		fr.close();
		fr = new BufferedWriter(new FileWriter("./target/archive/failedRCs.txt", true));
		fr.write(rc + "\n");
		fr.close();
	}

}
