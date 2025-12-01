# PAZ1LukIva

#14.10 (1 day)

- Urobili sme GitHub a spolocny projekt v Intellij IDEA
- Urobili sme zaciatok schemy https://www.figma.com/board/YrMwK2uwy8JatE7ASamVXa/PAZPROJECT?node-id=0-1&t=z6C295pHI19aMmez-1
- Dohodnuli sme sa o planach a ulohach kazdeho

PLAN NA NEXT DAY
- Dopisat schemu

  
------------------------------------------------------------------------------------------------------------------------

#15.10 (2 day)
- Zacali sme kodit, urobili sme triedy atd.
- Dohodnuli sme sa ako to budeme robit
- Riesili problemu s DAtabazamy (robime NoSQL)

PLAN NA NEXT DAY
- Zacat pisat logiku
- Pridat DAO

------------------------------------------------------------------------------------------------------------------------
#16.10 (3 day)
- Urobili sme generaciu Hodin a Otazok
- Databaza s notami
- Class Note, Lesson, Quiz
- NoteDAO
- Random generacia

PLAN NA NEXT DAY
- Urobit moznost ulozit Lessons v databazu
- Pocet tlacidiel v zavislosti od pocetu Not na Hodine

------------------------------------------------------------------------------------------------------------------------

#17.10 (4 day)

- prepojili sme GUI s javou
- spravili sme controller pre main scene (na ovladanie tlacidiel)

PLAN NA NEXT DAY
- Urobit controler na traning
- Urobit samozrejme traning
- Dopisat do dabazy noty

------------------------------------------------------------------------------------------------------------------------

#18.10 (5 day)
- Urobili sme training mode
- Dopisali sme databazu not na pianino
- Riesili sme ulohu "Dynamic buttons" (pocet tlacidiel v zavislosti od parametra)

PLAN NA NEXT DAY
- Riesit problemu spustenia dvoch a viac not
- Multiprocesing na Sounder.java
- Zlepsit dizajn na Training

------------------------------------------------------------------------------------------------------------------------

#19.10 - 21.10 (6-8 day)

WEEKEND

PLAN NA NEXT DAY
- Factory a spravne DAO
- Therads na noty

------------------------------------------------------------------------------------------------------------------------

#22.10 (9 day)
- Pridali sme Theards na hranie Not
- Ine drobne zmeny

PLAN NA NEXT DEN JE STALY
- Factory a spravne DAO

------------------------------------------------------------------------------------------------------------------------

#23.10 (10 day)

SME LENIVI
NA BUDUCE NE BUDEM PISAT WIKENDY

------------------------------------------------------------------------------------------------------------------------

#24.10 (11 day) 
- Factory (nespravne)
- Usporiadania not pomocou filtra
- Nechame DAO ako je

PLAN NA NEXT DAY
- Zacat urobit jednoduchy kviz
- Popratat bordel v suborach
- Upravit sortovanie lebo sortuje zle -> (A#0, A0), ma byt takto -> (A0, A#0)

------------------------------------------------------------------------------------------------------------------------

#Noc z 25.10 na 26.10 (12-13 day)
- Spravne Factory.java
- Vytvaranie roznych typov Sounder.java
- Usporiadanie suborov
- InstrumentType.java pridana, 2 enum
- Training typov PIANINO, GUITAR, VIOLIN, FLUTE 
- Noviy metod volanie zvuku
- Normalne usporiadania not v trainingu

PLAN NA NEXT DAY
- Zacat urobit jednoduchy kviz

------------------------------------------------------------------------------------------------------------------------

#1.11 (14 day)
- Prvy jednoduchy kviz
- Figma projekt https://www.figma.com/design/JK86CwmlSRggJLxtku6fsS/Prezentacia-LukIva?node-id=0-1&t=A0wvit0VUzHDLp0Q-1
- LessonWindow a LessonController triedy
- Premenovania Quiz -> Question | Lesson -> Quiz
- DOHODA MEDZI LUKASOM A IVANOM

PLAN NA NEXT DAY
- Databse
- LessonCreator view a logiku
- ZLEPSIT KOD!! 

------------------------------------------------------------------------------------------------------------------------

#8.11 (15) day
- Zlepseniy kod
- NoteDAO ma novy funkcii
- QuestionDAO, QuizDAO save and load
- Fungujuca databaza
- Spraveny melky chyby

PLAN NA NEXT DAY
- Fix main okienka
- QuizCreator view a logiku
- Statistika pre Noty

------------------------------------------------------------------------------------------------------------------------

#10.11 (16 day)
- Testy pre NoteDAO.java, QuizDAO.java a QuestionDAO.java
- Fix okienok
- Setting okienko (bez logiky)
- Viac citatelniy kod, mini opravy
  
PLAN NA NEXT DAY
- QuizCreator view a logiku
- Statistika pre Noty
- Setting okienko logika
- Database na setting a stats

------------------------------------------------------------------------------------------------------------------------

#18.11(17 day)
- QuizEditor s jednoduchym dizajnom
- sme lenivy preto tak malo

DOLEZITIY PLAN NA NEXT DAY
- Note picker pre QuizEditor
- FreqNote pre QuizEditor
- Ulozenie Quiza do database od QuizEditora

------------------------------------------------------------------------------------------------------------------------

#19.11 (18 day)
- Uprava okienok v QuizEditor
- Moznost vymazania Questiona v QuizEditore
- Dynamicke vytvaranie a premenovanie VBoxov
- +1 Test pre NoteDAO getNoteFromQuiz
- SqlDAO.java trieda pri prace z sql vseobecne (dropTables, getConnection)
lebo pred tim tieto func. boli v triede QuizDAO.java
- pridanie css

DOLEZITIY PLAN NA NEXT DAY TAKY ISTY xD

~~- Note picker pre QuizEditor~~
- FreqNote pre QuizEditor
- Ulozenie Quiza do database od QuizEditora
- Functions todo
- Loggs
- Training.java usporiadanie not podla sharp a base

------------------------------------------------------------------------------------------------------------------------

#23.11 (19 day) IVAN
- Na pol urobene QuizEditor
- Pridanie ContainerBox<String> freqNote
- Na pol kontrola pre spravny vytvoreny Quiz
- Novy func. v Functions(not used) a v NoteDAO
- Tests pre novy func.
- 23.11 trosku neskor bola najdena chyba pri ulozeni quiza s freqNote = null
- Rozsirenie okienka questionBox v QuizEditore


PLAN NA NEXT DAY
- Loggs
- Training.java usporiadanie not podla sharp a base
- Uozenia quizov do main menu
- Spravne Functions lebo mame tam zle!
- Pridanije logiky "not used two times" v QuizEditor
- Moznost vybera freqNoty like null
- Testy na settings
- Urobeniy stats

------------------------------------------------------------------------------------------------------------------------

#25.11 (20 day)
- DisplayName Not podla Settingu (#->b)
- SettingDAO a TestsSQL
- Pridania triedy Settings
- Ulozwnije settings a citanie Settings
- Save and Exit button
- Hlasitost podla settings

PLANY SU TAKY ISTY
- Loggs
- Training.java usporiadanie not podla sharp a base
- Uozenia quizov do main menu
- Spravne Functions lebo mame tam zle!
- Pridanije logiky "not used two times" v QuizEditor
- Moznost vybera freqNoty like null
- Urobeniy stats

------------------------------------------------------------------------------------------------------------------------

#26.11 (21 day or KATASTROFA)
- Snazil som sa pridat Logs, prvy a posledny krat...
- Nerobime Logs
- Nikdy nebudu logs
- Cely den som robil logs, vysledok 0
- +1023 a -412 -> result = 0

PLANY SU TAKY ISTY
- Training.java usporiadanie not podla sharp a base
- Uozenia quizov do main menu
- Spravne Functions lebo mame tam zle!
- Pridanije logiky "not used two times" v QuizEditor
- Moznost vybera freqNoty like null
- Urobeniy stats
- QuizEditor zoznam not podla Settingu

------------------------------------------------------------------------------------------------------------------------

#27.11 (22 day)
- Spravne usporiadania Not v training
- Spravne Functions
- Moznost vybera freqNote like None
- QuizEditor zoznam podla settings
- Snazili sme sa pridat metod "not used two time" v QuizEditor no to sa vobec nedari
- NoteDAO.getNoteByName teraz vie najst aj notu ktora ma v nazov ♭
- Vypysanie quizov v MainController a moznost ich vybera
- Dark a Light tema (60%)
- pridanie singleton triedy WindowManager
- upravenie css suborov
- vytvorenie dark_theme.css
- pridavanie okien do WindowManager pri vytvarani stage

PLAN NA NEXT DAY
- Urobeniy stats
- Moznost hrat quiz

------------------------------------------------------------------------------------------------------------------------

#28.11 (23 day)
- Moznost hrania kvizu
- Ked je freqNote, tak pred zaciatkom otazky hra najprv ona
- Dynamicky update quizzov pri ich vytvoranii
- Sounder hra noty podla question.InstrumentType
- trieda Quiz ma instancnu premenu ID
- Progress bar
- Life system
- Label na FreqNotu a Lives

PLAN NA NEXT DAY
- Intro quiz window
- Stats

------------------------------------------------------------------------------------------------------------------------

#1.12 (24 day winter)
- Testy pre Functions
- Dynamycke vymazanie quizu (vymazanie quizu, vsetkych otazok quizu a vsetko questions_has_notes)
- Peknejse okienko quizu
- Testy pre Functions a Factory

PLAN NA NEXT DAY
- Intro quiz window
- Stats
- ViacJazykovost
- Pekny front-end
