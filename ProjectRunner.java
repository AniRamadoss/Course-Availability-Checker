package SeleniumProj;

import java.util.ArrayList;

public class ProjectRunner {
    public static void main(String[] args) {
        // Stores the classes
        ArrayList<String[]> classes = new ArrayList<String[]>();
        // Put the email you would like to receive notifications to here.
        String email =
            "Replace this with the email that notifications need to be sent to. ";
        /**
         * Add more classes below these two lines in a similar format
         * First entry of string array should be class Department text found as
         * in
         * https://apps.es.vt.edu/ssb/HZSKVTSC.P_DispRequest
         * Second entry should be class number, third is semester, fourth is
         * CRN.
         */
        String[] class1 = { "CS - Computer Science", "2505", "Fall 2021", "" };
        classes.add(class1);

        String[] class2 = { "MATH - Mathematics", "2534", "Fall 2021", "" };
        classes.add(class2);

        String[] class3 = { "", "", "Fall 2021", "86623" };
        classes.add(class3);

        for (int i = 0; i < classes.size(); i++) {
            String classDepartment = classes.get(i)[0];
            String classNumber = classes.get(i)[1];
            String term = classes.get(i)[2];
            String courseRegistrationNumber = classes.get(i)[3];

            new Thread(() -> {
                new ClassChecker(classDepartment, classNumber, term,
                    courseRegistrationNumber, email);
            }).start();

            try {
                Thread.sleep(25000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
