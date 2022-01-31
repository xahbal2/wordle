import java.util.*;

public class Util {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }


        return result;
    }

    public static List<String> sortResult (List<String> guessedList){
        List<Character> freqList = List.of('s','e','a','o','r','i','l','t','n','d','u','c','p','y','m','h','g','b','k','f','w','v','z','x','j','q');
        Map<String,Integer> freqMap = new HashMap<>();
        for(String a : guessedList){
//            Set<Character> set = new HashSet<>();
//            for(Character c : a.toCharArray()){
//                set.add(c);
//            }
            int feq = 0;
            for(Character b : a.toCharArray()){
                feq += freqList.indexOf(b);
            }
            freqMap.put(a,feq);
        }
        freqMap = sortByValue(freqMap);
        List<String> result = new ArrayList<String>(freqMap.keySet());
        return result;
    }

}
