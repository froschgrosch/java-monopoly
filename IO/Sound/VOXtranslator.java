package Sound;
public class VOXtranslator
{

    public VOXtranslator(){}

    public String[] toStringArr(int number) {
        String[] output = new String[0];
        int cl = 0; // currentLength
        /* Diese Funktion wandelt Integer von 0 bis 999999999
         * in ein String-Array mit passenden Dateinamen um,
         * so dass man Zahlen mit der Half-Life VOX sagen kann
         * (Teil der Sprachausgabe) */
        if (number < 0) { // negative Nummer, geht nicht 
            output = new String[2];
            output[0] = "illegal.wav";
            output[1] = "number.wav";
        } else if (number <= 20) { // end if (number < 0)
            output = new String[1];
            output[0] = String.valueOf(number)+".wav";
            cl++;
        } else if (number < 100) { // end if (number <= 20)
            if (number % 10 == 0) { // 30 40 50 ... haben eigene Dateien
                output = new String[1];
                output[cl] = String.valueOf(number)+".wav";
                cl++;
            } else { // end if (number % 10 == 0)
                output = new String[2];  // sonst aufteilen in Zehner und Einer (30 5)
                String[] temp = toStringArr(number % 10); // erste Stelle ist eine Ziffer lang, rekursiv aufrufen
                output[cl] = String.valueOf(number - number % 10)+".wav";
                cl++;
                output[cl] = temp[0];
                cl++;
            } // end else
        } else if (number < 1000) { // 100 bis 999
            if (number % 100 == 0) { // 100 200 300 ... Durch 100 teilen -> 3 hundred
                output = new String[2];
                output[cl] = String.valueOf(number/100)+".wav";
                cl++;
                output[cl] = "hundred.wav";
                cl++;
            } else {  // end if (number % 100 == 0)
                if (number % 10 == 0) { // wenn vielfaches von 10
                    output = new String[3];
                    String[] temp = toStringArr(number - number % 100); // Abspalten 3|50 -> 300
                    for (int i = 0; i < temp.length; i++) {
                        output[cl] = temp[i];
                        cl++;
                    } // end for
                    output[cl] = String.valueOf(number % 100)+".wav"; // 10er Rest umwandeln - kann nur ein Vielfaches 10 sein, also eine Stelle lang
                } else { // end if (number % 10 == 0) wenn kein Vielfaches von 10
                    output = new String[4];
                    if (number % 100 < 10) { // Bugfix: Bei 3 Stellen wenn 2 Stelle Null entsteht leeres Feld
                        output = new String[3];
                    }
                    String[] temp = toStringArr(number - number % 10); // rekursiv aufrufen mit letzer Stelle auf 0 - vielfaches von 10
                    for (int i = 0; i < temp.length; i++) {
                        output[cl] = temp[i];
                        cl++;
                    } // end for
                    output[cl] = String.valueOf(number % 10)+".wav"; // Einfügen letze Stelle
                } // end else
            } // end else
        } else if (number < 1000000) { // end if (number < 1000)
            if (number % 1000 == 0) {
                String[] prefix = toStringArr(number / 1000); // Verarbeite 10|000 -> 10
                output = new String[prefix.length+1];
                for (int i = 0; i < prefix.length; i++) {
                    output[cl] = prefix[i];
                    cl++;
                } // end for
                output[cl] = "thousand.wav";
                cl++;
            } else if (number % 100 == 0) {// end if (number % 1000 == 0)
                String[] prefix = toStringArr(number - number % 1000); // Aufspalten in 10|200 -> 10
                String[] suffix = toStringArr(number % 1000); // und 10|200 -> 200
                output = new String[prefix.length+suffix.length]; // anschließend Ergebnisse zusammenführen
                for (int i = 0; i < prefix.length; i++) {
                    output[cl] = prefix[i];
                    cl++;
                } // end for
                for (int i = 0; i < suffix.length; i++) {
                    output[cl] = suffix[i];
                    cl++;
                } // end for
            } else if (number % 10 == 0) { // end else if (number % 100 == 0)
                String[] prefix = toStringArr(number - number % 100); // Aufspalten in 35|50 -> 3500
                String[] suffix = toStringArr(number % 100); // und 35|50 -> 50
                output = new String[prefix.length+suffix.length]; // anschließend Ergebnisse zusammenführen
                for (int i = 0; i < prefix.length; i++) {
                    output[cl] = prefix[i];
                    cl++;
                } // end for
                for (int i = 0; i < suffix.length; i++) {
                    output[cl] = suffix[i];
                    cl++;
                } // end for
            } else { // end else if (number % 10 == 0)
                String[] prefix = toStringArr(number - number % 10); // Aufspalten in 568|9 -> 5680
                String[] suffix = toStringArr(number % 10); // und 568|9 -> 9
                output = new String[prefix.length+suffix.length]; // Anschließend Ergebnisse zusammenführen
                for (int i = 0; i < prefix.length; i++) {
                    output[cl] = prefix[i];
                    cl++;
                } // end for
                for (int i = 0; i < suffix.length; i++) {
                    output[cl] = suffix[i];
                    cl++;
                } // end for
            } // end else
        } else if (number < 1000000000) { // end else if (number < 1000000)
            String[] prefix = toStringArr((number - number % 1000000)/1000000); // Aufspalten in 17|238839 -> 17
            String[] suffix = toStringArr(number % 1000000); // und 17|238839 -> 238839
            if (number % 1000000 == 0){ // Bugfix 1 million 0 -> 1 million
                suffix = new String[0];
            }
            output = new String[prefix.length+suffix.length+1]; // Anschließend Reihenfolge prefix "million" suffix
            for (int i = 0; i < prefix.length; i++) {
                output[cl] = prefix[i];
                cl++;
            } // end for
            output[cl] = "million.wav";
            cl++;
            for (int i = 0; i < suffix.length; i++) {
                output[cl] = suffix[i];
                cl++;
            } // end for
        } // end else if (number < 1000000000)
        return output;
    } // Ende Funktion
}
