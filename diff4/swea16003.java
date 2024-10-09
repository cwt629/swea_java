/* 16003. 화면 캡쳐 */

import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static BufferedWriter bw;
    static int count = 50;
    
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
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
            count = 50; // 카운트 초기화
            int N = Integer.parseInt(br.readLine());
			bw.write("#" + test_case);
			for (int i = 1; i < 10 && i <= N; i++){
            	dfs(i, N);
            }
            bw.newLine();
		}
        
        bw.flush();
	}
    
    static void dfs(int n, int max) throws Exception{
        if (count <= 0) return;
        // 출력
        bw.write(" ");
        bw.write(n + ".png");
        count--;
        
        for (int add = 0; add < 10 && n * 10 + add <= max && count > 0; add++){
            dfs(n * 10 + add, max);
        }
    }
}