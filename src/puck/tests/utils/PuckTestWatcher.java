package puck.tests.utils;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class PuckTestWatcher extends TestWatcher {

	private static final String EOL = 
	        System.getProperty("line.separator");
	 
	    private static StringBuilder builder = new StringBuilder();
	 
	    @AfterClass
	    public static void afterClass() {
	        System.out.print(builder);
	    }
	 
	    @Rule
	    public TestWatcher watchman = new TestWatcher() {
	 
	        @Override
	        protected void failed(Throwable e, Description description) {
	            if (description != null) {
	                builder.append(description);
	            }
	            if (e != null) {
	                builder.append(' ');
	                builder.append(e);
	            }
	            builder.append(" FAIL");
	            builder.append(EOL);
	        }
	 
	        @Override
	        protected void succeeded(Description description) {
	            if (description != null) {
	                builder.append(description);
	            }
	            builder.append(" OK");
	            builder.append(EOL);
	        }
	    };
}
