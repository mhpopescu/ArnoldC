Etapele programului:
1. Interpreter instantiaza AstBuilder care construieste arborele
	1.1 AstBuilder[4] instantiaza Parser care citeste din fisier si intoarce un token
		1.1.1 Parser citeste din fisier si parseaza comanda
			  Seteaza argumentul si tipul argumentului
			  Returneaza primul token gasit acceptat
	1.2 Principalele noduri sunt Main, Print, Declare, If si While
		Fiecare nod are cate o comanda make care il construieste cand este citit tokenul corespunzator
		Fiecare nod cere parserului alte tokenuri ca sa isi construiasca copiii

2. Intepreter instantiaza PrintVisitor care printeaza arborele
	PrintVisitor visiteaza fiecare copil si afiseaza tipul nodului si valoarea salvata in el
3. Interpreter instantiaza InterpreterVisitor care interpreteaza arborele
   Interpretorul nu ar trebui sa modifice valorile nodurilor din Ast, la o alta interpretare a lui sa dea acelasi rezultat[4]. De aceea imi tin valorile intr-un HashMap, iar cand vreuna se schimba, se updateaza valoarea din hash.
   Nodurile de comenzi (Print, Declare, If si While) executa comenzile prin apelarea metodei accept pentru ele. Fiecare isi viziteaza si copiii.
   Functiile de visit pentru frunze functioneaza ca niste Setteri pentru variabilele din interpreter, care sunt folosite in comenzi

Resources:
[1] AST: https://en.wikipedia.org/wiki/Abstract_syntax_tree

[2] Interpreter: https://en.wikipedia.org/wiki/Interpreter_(computing)

[3] Tipurile nodurilor: http://elf.cs.pub.ro/poo/_detail/teme/tema3/poo-tema3-diagram2.png?id=teme%3Atema3

[4] Ce face un parser: http://softwareengineering.stackexchange.com/questions/331253/how-is-a-abstract-syntax-tree-used-to-execute-source-code

[5] Un exemplu de creare a unui AST: http://stackoverflow.com/questions/25049751/constructing-an-abstract-syntax-tree-with-a-list-of-tokens

[6] Rularea unui AST: http://softwareengineering.stackexchange.com/questions/331253/how-is-a-abstract-syntax-tree-used-to-execute-source-code

[7] Visitor: https://en.wikipedia.org/wiki/Visitor_pattern

[8] Control flow: https://en.wikipedia.org/wiki/Control_flow_graph
