/*
문제 설명

점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost,
여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

제한사항

전체 학생의 수는 2명 이상 30명 이하입니다.
체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다.
이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
\*/

import java.util.HashSet;
import java.util.Set;

public class gym_suit {
    public static void main(String[] args) {
        int n = 5; // 전체 학생 수
        int[] lost = {2,3,4,5}; // 체육복을 도난당한 학생들의 번호
        int[] reverse = {1,3,5}; // 여벌 체육복을 가져온 학생들의 번호
        int result = gymsuit(n, lost, reverse);
        System.out.println("체육복을 입을 수 있는 최대 학생 수 "+result);
    }

    public static int gymsuit(int n,int[] lost, int[] reverse) {
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reverseSet = new HashSet<>();

        // 도난당한 학생과 여벌 체육복이 있는 학생을 세트로 관리
        for(int l : lost){
            lostSet.add(l);
        }


        for(int r : reverse){
            // 여벌 체육복을 가져왔지만 도난당한 학생 처리
            if(lostSet.contains(r)){
                lostSet.remove(r);
            }else{
                reverseSet.add(r);
            }
        }

        int count = n - lostSet.size();  // 체육복을 잃어버린 학생 수를 제외한 초기 학생 수

        // 여벌 체육복을 빌려줌
        for(Integer l : lostSet){
            if(reverseSet.contains(l-1)){
                reverseSet.remove(l-1);
                count++;
            }else if(reverseSet.contains(l+1)){
                reverseSet.remove(l+1);
                count++;
            }
        }
        return count;
    }
}
