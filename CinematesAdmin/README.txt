CREARE UNA CARTELLA CINEMATES_ADMIN 

il file jar ed il file config-properties devono essere predisposti nella cartella:

CINEMATES_ADMIN/

il file delle credenziali AWS sotto la cartella 

C:/Users/<accountUtente>/.aws

esempio:    C:/Users/nicola/.aws		(se i nomi sono in italiano ->   C:/Utenti/nicola/.aws)

PRIMA DI FARE QUESTO  va creata la cartella .aws sotto C:/Users/<accountUtente>

Nelle variabili di ambiente di windows (pannello di controllo -> Sistema ->
	impostazioni di sistema avanzate ) va creata la variabile di ambiente 
	AWS_PROFILE = produser

Per lanciare il programma desktop si può fare in due modi:

o doppio click sul jar

oppure aprendo la PowerShell di windows , posizionandosi sul path dove è 
presente il jar ( target)
invocare il  comando 

java -jar CinematesAdmin

Quest'ultimo comando fa vedere anche il log delle system out java

IN caso di problemi verificare le variabili di ambiente Java nel pannello di controllo

UN UTENTE PER FARE i TEST E' luigi.rossi@alice.it	pinco.123

FINE->

PS.
QUESTE ISTRUZIONI VANNO FATTE SOLO PER FARE LA BUILD DA ECLIPSE (non occorrono 
per lanciare il programma)

Per produrre il jar da eclipse , si va sul menu file e si fa "Export" come 
Runnabla JAR File
e si seguono le indicazioni
Di solito il file jar lo si esporta nella cartella target e li va anche messo il config.properties

Caso mai non funzionasse la compilazione
Nelle impostazioni del progetto va predisposta la compatibilita' con java 1.8


