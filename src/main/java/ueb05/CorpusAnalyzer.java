package ueb05;

import java.util.*;

public class CorpusAnalyzer {
	private List<String> theses;

	CorpusAnalyzer(Iterator<String> thesesIterator) {
		// TODO Alle Titel in die this.theses Liste übernehmen
		theses = new LinkedList<>();
		while(thesesIterator.hasNext()){
			theses.add(thesesIterator.next());
		}
	}

	/**
	 * Gibt die Anzahl der angefertigten Theses zurück
	 */
	int countTheses() {
		return theses.size();
	}
	/**
	 * Gibt die durchschnittliche Länge von Titeln in Worten zurück
	 */
	int averageThesisTitleLength() {
		// Map mit <title, wortlaenge>
		Map<String, Integer> titelWorte = new HashMap<>();
		for (String s : theses) {
			String[] sSplit = s.split(" ");
			titelWorte.put(s, sSplit.length);
		}
		//Collection mit int wortlaenge
		Collection<Integer> v = titelWorte.values();
		int laengeGesamt = 0;
		for (int i :
			 v) {
			laengeGesamt += i;
		}
		return laengeGesamt/theses.size();


	}

	/**
	 * Gibt eine alphabetisch absteigend sortierte und duplikatfreie
	 * Liste der ersten Wörter der Titel zurück.
	 */
	List<String> uniqueFirstWords() {
		// Set mit duplikatfreien ersten Wörtern
		Set<String> firstWords = new TreeSet<>();
		for (String s : theses) {
			String[] sSplit = s.split(" ");
			firstWords.add(sSplit[0]);
		}
		List<String> unsortedList = new ArrayList<>();
		for (String s: firstWords){
			unsortedList.add(s);
		}
		Collections.sort(unsortedList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.toString().compareTo(o1.toString());
			}
		});

		return unsortedList;
	}

	/**
	 * Gibt einen Iterator auf Titel zurück; dabei werden alle Woerter, welche
	 * in `blackList` vorkommen durch Sternchen ersetzt (so viele * wie Buchstaben).
	 */
	Iterator<String> censoredIterator(Set<String> blackList) {
		Set<String> censoredList = new TreeSet<>();
		for (String s : theses) {
		    for (String t : blackList) {
		        if (s.contains(t)){
		            String censored = "";
		            for (int i = 0; i < t.length(); i++) {
		                censored += "*";
                    }
		            s = s.replaceAll(t, censored);
                }
            }
            censoredList.add(s);
        }
        return censoredList.iterator();
	}

	/**
	 * Gibt eine Liste von allen Titeln zurueck, wobei Woerter so ersetzt werden,
	 * wie sie in der Map abgebildet werden.
	 */
	List<String> normalizedTheses(Map<String, String> replace) {
		List<String> normList = new LinkedList<>();
		for (String s : theses) {
		    Set<String> keys = replace.keySet();
		    for (String k : keys) {
		        if (s.contains(k)){
		            s = s.replaceAll(k, replace.get(k));
                }
            }
            normList.add(s);
        }
    return normList;
	}
}
