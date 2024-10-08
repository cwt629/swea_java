/* 20936. 상자 정렬하기 */

import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		int T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
            int[] boxes = new int[N + 1]; // 각 보관함에 든 상자의 번호 - 1(없으면 N)
            int[] indices = new int[N]; // 각 번호의 상자가 위치해있는 보관함에서의 인덱스 배열
            int next = -1; // 다음으로 상자를 옮길 보관함 칸의 번호
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int matches = 0;
            for (int i = 0; i < N; i++){
            	int boxNumber = Integer.parseInt(st.nextToken());
                boxes[i] = boxNumber - 1;
                indices[boxes[i]] = i;
                
                // 박스 번호와 보관함 번호가 일치하지 않으면, 한번이라도 sort 과정이 필요하다
                if (boxes[i] != i){
                	next = i;
                }
                else matches++;
            }
            boxes[N] = N;
            
            int empty = N; // 비어있는 보관함 칸의 인덱스
            ArrayList<Integer> log = new ArrayList<>();
            
            while (matches < N){
                log.add(next + 1);
                // indices 먼저 갱신해준다
                indices[boxes[next]] = empty;
                // boxes에서 둘을 서로 바꾼다
                swapInArray(boxes, empty, next);
                if (empty < N && boxes[empty] == empty) matches++; // 맞춰진 경우
                
                empty = next;
                // 다음 next 찾기
                if (empty < N)
                    next = indices[empty];
                else {
                    if (matches >= N) break;
                	// 앞에서부터, 번호가 맞지 않는 next 탐색
                    for (next = 0; next < N && boxes[next] == next; next++);
                }
            }
            
            bw.write(String.valueOf(log.size()));
            bw.newLine();
            String logString = "";
            for (int i = 0; i < log.size(); i++){
            	if (i > 0) logString += " ";
                logString += String.valueOf(log.get(i));
            }
            
            bw.write(logString);
            bw.newLine();
		}
        
        bw.flush();
	}
    
    // 배열의 두 원소를 서로 swap하는 함수
    static void swapInArray(int[] arr, int index1, int index2){
    	int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}