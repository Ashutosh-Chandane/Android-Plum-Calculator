package com.example.plumcalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends ActionBarActivity {
    TextView disp;
    static int result=0;
    private int NumberBf;
    private String firstOperator = "";
    private String initialOperator="";
    boolean isOperation=false;
    public ButtonClickListener btnClick = new ButtonClickListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        disp=(TextView)findViewById(R.id.textView);
        disp.setText("0");

        final int idList[]={R.id.button,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,
                R.id.button8,R.id.button9,R.id.button10,R.id.button11,R.id.button12,R.id.button13,R.id.button14};
        for(int id:idList){
            View v=(View)findViewById(id);
            v.setOnClickListener(btnClick);
        }
    }

    public void doMath(String op){
    	
    		NumberBf = Integer.parseInt(disp.getText().toString());// save the screen
    		calResult();
            if(op.equals("=")) {
                NumberBf = 0;
                result = 0;
                //firstOperator="=";
            }
    		
    }

public void getKeyboard(String numb){
    String ScrCurrent= disp.getText().toString();
    
    if(ScrCurrent.equals("0")) {
        ScrCurrent="";
    }
    // to avoid numbers to get added after equals
	if(!firstOperator.equals("=") /*&& initialOperator.equals("")*/) {
		if(initialOperator.equals("-"))
		{
			numb = "-"+numb;
			initialOperator = "";
		}
		
	    ScrCurrent += numb;
	    disp.setText(ScrCurrent);
	}

}
    public void calResult(){
        /*if(initialOperator.equals("-")){
        	NumberBf = -NumberBf;
        }*/
    	if(firstOperator.equals("+") || firstOperator.equals("")){
            result = result + NumberBf;
        }
        if(firstOperator.equals("-")){
                result =result - NumberBf;
        }
        if(firstOperator.equals("="))
        {
            result=NumberBf;

        }
 /*       if(result == 0)
        {
        	firstOperator = "";
        }*/	
        disp.setText(String.valueOf(result));

    }

    private class ButtonClickListener implements View.OnClickListener{
    @Override
    public void onClick(View v){

            switch (v.getId()) {
                case R.id.button13: //for clearing screen
                    result = 0;
                    disp.setText("0");
                    NumberBf = 0;
                    firstOperator = "";
                    initialOperator = "";
                    break;
                case R.id.button4: // addition
                    if(disp.getText().toString().equals("0")){
                    	initialOperator = "+";
                    }
                    else{
                    	if(!isOperation) {
                            doMath("+");
                        }
                        firstOperator = "+";
                        isOperation = true;
                    }
                    break;
                case R.id.button10:
                	if(disp.getText().toString().equals("0")){
                    	initialOperator = "-";
                    }
                    else{
                    	if(!isOperation)
                        {
                    		doMath("-");
                        }
                        firstOperator = "-";
                        isOperation = true;
                    }
                    break;
                case R.id.button14://equals
                	if(result !=0){
                		if (isOperation) {
                            break;
                        }
                		doMath("=");
                    }
                         break;
                default:
                    String numb = ((Button) v).getText().toString();
                    if (isOperation) { // isoperator means you have a operator and then a number.
                        disp.setText(""); // set screen to blank to show next number
                        isOperation = false;
                    }

                    getKeyboard(numb);
                    break;
            }

    }

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
