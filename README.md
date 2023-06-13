# siw-personal-project

Progetto: Sito di Gestione Annunci di Lavoro (per ora bozza, ps. mi scorderò di aggiornare il readme e ci sarà scritto ancora che è una bozza :)  )

Casi d'uso:

1. Inserimento di un annuncio da parte di un'azienda:
   - L'azienda accede all'area riservata e seleziona la funzione di inserimento di un nuovo annuncio.
   - L'azienda completa un modulo contenente le informazioni sull'annuncio, come titolo, descrizione, settore, requisiti richiesti e altri dettagli pertinenti.
   - Il sistema valida e registra l'annuncio nel database.

2. Aggiornamento di un annuncio da parte di un'azienda:
   - L'azienda accede all'area riservata e seleziona l'annuncio che desidera aggiornare.
   - L'azienda modifica le informazioni necessarie dell'annuncio, ad esempio il titolo, la descrizione o i requisiti richiesti.
   - Il sistema valida e aggiorna le informazioni dell'annuncio nel database.

3. Lettura degli annunci disponibili per le persone in cerca di lavoro:
   - Le persone in cerca di lavoro accedono al sito e selezionano la sezione degli annunci di lavoro.
   - Il sistema mostra un elenco di annunci disponibili, con i dettagli di base come il titolo e il settore.
   - Le persone in cerca di lavoro possono cliccare su un annuncio per visualizzare ulteriori dettagli come la descrizione e i requisiti richiesti.

4. Ricerca degli annunci per settore o parola chiave:
   - Le persone in cerca di lavoro utilizzano la funzione di ricerca per trovare annunci specifici.
   - Il sistema consente loro di inserire una parola chiave o selezionare un settore specifico.
   - Il sistema mostra gli annunci corrispondenti alle preferenze di ricerca specificate.

Modello di dominio:

1. Entità:
   - Annuncio: rappresenta un annuncio pubblicato da un'azienda e contiene informazioni come titolo, descrizione, settore, requisiti richiesti e altro.
   - Azienda: rappresenta un'azienda che pubblica annunci di lavoro e contiene informazioni come nome, descrizione e altre informazioni di contatto.
   - Utente: rappresenta una persona registrata nel sistema, che può essere sia un'azienda che una persona in cerca di lavoro. Contiene informazioni come nome, email e password.

2. Relazioni:
   - Un'azienda può pubblicare molti annunci di lavoro.
   - Un annuncio è associato a un'unica azienda.
   - Una persona in cerca di lavoro può visualizzare molti annunci e aderire a quelli che sono interessanti.

Strato di persistenza:

1. Tabella "Annunci":
   - Colonne: ID annuncio, ID azienda, titolo, descrizione, settore, requisiti richiesti, data di pubblicazione, ecc.

2. Tabella "Aziende":
   - Colonne: ID azienda, nome, descrizione, indirizzo, contatti, ecc.

3. Tabella "Utenti":
  - Colonne: ID utente, nome, email, password, ruolo (azienda o persona in cerca di lavoro), ecc.

4. Tabella "Partecipazioni":
   - Colonne: ID partecipazione, ID annuncio, ID utente, data di partecipazione, stato (accettato, in attesa, rifiutato), ecc.
