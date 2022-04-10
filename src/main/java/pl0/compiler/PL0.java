package pl0.compiler;

import java.lang.*;

public class PL0 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("컴파일 대상 파일을 입력해주세요.");
            return;
        }

        boolean symTable = false;  /* true 이면 각 블록의 기호표를 출력한다 */
        boolean objCode = false;   /* true 이면 목적코드를 출력한다 */
        boolean trace = false;     /* true 이면 실행 트레이스 정보를 출력한다 */
        boolean byteCode = false;  /* true 이면 목적코드를 바이트코드로 한다 */

        String s = args[0];
        if (s.charAt(0) == '-') {
            for (int j = 1; j < s.length(); j++) {
                switch (s.charAt(j)) {
                    case 's': symTable = true; break;
                    case 'o': objCode = true; break;
                    case 't': trace = true; break;
                    case 'b': byteCode = true; break;
                }
            }
            s = args[1];
        }

        System.out.println("컴파일 대상 파일: " + s);

        GetSource source =  new GetSource();
        Table table = new Table(source, symTable);
        source.set(table);
        CodeGen codeGen;
        if (byteCode)
            codeGen = new CodeGenB(source, table, trace);
        else
            codeGen = new CodeGen(source, table, trace);
        Compile compiler = new Compile(source, table, codeGen, objCode);
        if (!source.openSource(s))
            return;
        if (compiler.compile())
            codeGen.execute();
        source.closeSource();
    }
}
