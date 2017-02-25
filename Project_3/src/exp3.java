import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben_Big on 2/25/17.
 */
public class exp3 {
    public final static double INTERVAL=0.25;
    private double limit=INTERVAL;
    private lineObjectAnalyzer analyzer = new lineObjectAnalyzer();
    private expThreeDataPointsCollector collector=new expThreeDataPointsCollector();

    public void addNewLine(lineObject line){


        if (line.time>=limit){
            saveReset();
        }
        analyzer.processNewLine(line);
    }

    private void saveReset(){
        double time=limit;
        List<Float> currentResult=new ArrayList<>();
        currentResult.add(analyzer.getDropRate());
        currentResult.add(analyzer.getAverageRTT());
        currentResult.add((float)analyzer.getThroughput());
        this.limit+=INTERVAL;
        analyzer.reset();
        collector.addResult(time,currentResult);
    }


    public  void runTCLFile(String command) {
        String line="";
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            while ((line = reader.readLine()) != null) {
                if (lineObjectAnalyzer.findLinesOfInterest(line)) {
                    //System.out.println(line);
                    lineObject newLine = new lineObject(line);
                    this.addNewLine(newLine);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public static void main(String[] args){
        exp3 e=new exp3();
        String command="ns experiment3.tcl DropTail Reno";
        e.runTCLFile(command);
        System.out.print(e.collector.toString());
    }
}
