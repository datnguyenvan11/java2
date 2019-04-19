
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<View> viewArrayList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/tomandjerry.txt"));
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            if (count == 0) {
                count++;
                continue;
            }
            String[] splitted = line.split("\\s{2,}");
            if (splitted.length == 4) {
                String day = splitted[0];
                String id = splitted[1];
                String title = splitted[2];
                String View = splitted[3];
                double d = Double.parseDouble(View);
                View view = new View(day, id, title, d);
                viewArrayList.add(view);
            }
        }
        HashMap<String, ViewPunishment> statistic = new HashMap<>();
        for (View view : viewArrayList) {
            double currentView = 0;
            if (statistic.containsKey(view.getId())) {
                ViewPunishment viewPunishment = statistic.get(view.getId());
                currentView = viewPunishment.getView();
            }
            currentView = currentView + view.getView();
            ViewPunishment viewPunishment = new ViewPunishment(view.getId(), view.getTitle(), currentView);
            statistic.put(view.getId(), viewPunishment);
        }

        for (String id :
                statistic.keySet()) {
            ViewPunishment viewPunishment = statistic.get(id);
            System.out.printf("%15s|%15s|%15f \n", id, viewPunishment.getTitle(), viewPunishment.getView());

        }

    }

}
