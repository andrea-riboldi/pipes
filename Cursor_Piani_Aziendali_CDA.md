# Cursor — Piani business per la valutazione di acquisto
## Documento di supporto al Consiglio di Amministrazione

**Oggetto:** approvazione dell’acquisto di una o due suite corporate Cursor (Teams e/o Enterprise)  
**Destinatari:** Consiglio di Amministrazione  
**Lingua:** italiano  
**Nota:** i prezzi indicati sono quelli pubblicati da Cursor (The X Subscription / Team Pricing) e sono espressi in USD, salvo tasse e oneri applicabili. L’Enterprise richiede preventivo commerciale.

---

## 1. Executive summary

Cursor offre due piani orientati alle organizzazioni: **Teams** (acquisto e gestione in self-service) ed **Enterprise** (contratto su misura con Cursor Sales). Entrambi consentono l’adozione controllata di strumenti di sviluppo assistiti dall’IA (IDE, agenti, automazioni, code review) con fatturazione centralizzata, amministrazione del team e requisiti di sicurezza progressivamente più stringenti.

| Scenario | Piano suggerito |
|----------|-----------------|
| Team di sviluppo con budget prevedibile, SSO e governance “standard”, senza obbligo di fatturazione enterprise o SCIM | **Teams** (1–2 suite con mix di tier Premium / Super / Ultra) |
| Organizzazione con pooling dell’utilizzo IA, fatturazione via fattura/PO, provisioning SCIM, audit, controlli granulari su modelli e infrastruttura agenti | **Enterprise** |

**Proposta di delibera (indicativa):** autorizzare l’acquisto di **una suite Teams** per un numero definito di posti attivi (es. mix Premium/Super), con eventuale **seconda suite** o upgrade a **Enterprise** qualora emergano requisiti di compliance, SCIM o usage pooling non soddisfatti da Teams. Per Enterprise è necessario avviare il percorso commerciale (`enterprise@cursor.com` / contact sales).

---

## 2. Panorama dei piani business

Cursor distingue:

1. **Teams** — piano business self-service, prezzo per **posto attivo** al mese, tier mescolabili nel medesimo team.
2. **Enterprise** — piano custom, **nessun prezzo pubblico**; include tutto Teams più controlli, pooling, fatturazione enterprise e supporto dedicato.

I piani individuali (Free, Plus, Premium, Super, Ultra) restano rilevanti solo per utenti non in team o per i titolari di **posti Free** in Teams.

---

## 3. Tabella comparativa (Teams vs Enterprise)

| Area | Teams | Enterprise |
|------|-------|------------|
| **Modello di acquisto** | Self-service | Contratto / preventivo (contact sales) |
| **Prezzo pubblico** | Sì (per tier di posto) | No (custom) |
| **Posti / fatturazione** | Per **posto attivo** (non pre-allocati); upgrade/downgrade pro-rata | Per posto + allotment usage; pre-commit opzionale per volumi elevati |
| **Tier posto Teams** | Premium $30, Super $100, Ultra $200/mese; Free $0; mix consentito | Allineato al contratto commerciale |
| **Usage incluso (modelli Cursor)** | Pool **settimanale per posto** (non trasferibile tra utenti) | **Usage pooled** condiviso tra tutti gli utenti del team |
| **Usage oltre quota / modelli third-party** | Saldo **prepagato condiviso** dal team; top-up e limiti mensili | Pooled + controlli admin-only; limiti team e per utente |
| **Fatturazione centralizzata** | Sì | Sì |
| **SSO (SAML/OIDC)** | Sì | Sì |
| **SCIM / provisioning automatico** | No | Sì |
| **Fattura / Purchase Order** | Tipicamente carta / self-service | Sì |
| **Privacy Mode** | Enforce **obbligatorio** a livello team | Enforce org-wide |
| **Retention dati chat** | Personalizzabile (team) | Esteso (org) |
| **Admin API / analytics** | Usage stats, Admin API | Analytics + **AI Code Tracking API**, **Conversation Insights** |
| **Audit log** | — | Sì |
| **Cursor Blame** | — | Sì |
| **Bugbot (code review agentica)** | Sì | Sì |
| **Cloud Agents / automazioni** | Sì (contesto team condiviso) | Sì + **self-hosted machines**, **Team Pools** |
| **Team marketplace** (rules, skills, plugin) | 1 marketplace team | Marketplace **illimitati**; distribuzione/gating via SCIM |
| **Controlli modelli / run mode / repo / MCP** | Limitati rispetto a Enterprise | **Granulari** (Model Access Control, Run Mode, Repository Blocklist, MCP Configuration) |
| **Crittografia (CMEK)** | — | **Customer Managed Encryption Keys** |
| **Service accounts** | — | Sì |
| **Billing groups** | — | Sì |
| **Supporto** | Standard | **Prioritario** + **account manager** |
| **Sconti volume** | Contattare `enterprise@cursor.com` per team grandi | Negoziabili |

---

## 4. Piano Teams — dettaglio

### 4.1 Modello economico

- **Canale:** self-service (consigliato quando l’organizzazione può gestire autonomamente amministrazione e sicurezza “standard”).
- **Unità di fatturazione:** **posto attivo** al mese (non posti pre-assegnati vacanti). Aggiunte, rimozioni e cambi tier generano adeguamenti pro-rata nel ciclo di fatturazione.
- **Tier di posto (mensile, USD):**

| Tier posto | Prezzo/posto/mese | Usage settimanale modelli Cursor |
|------------|-------------------|----------------------------------|
| **Premium** | $30 | Allineato al piano individuale Premium |
| **Super** | $100 | ~3,75× Premium |
| **Ultra** | $200 | ~12,5× Premium |
| **Free** | $0 | Nessuna quota team; l’utilizzo del membro proviene dal **piano personale** (o dall’allowance Free individuale) |

- **Mix di tier:** consentito nello stesso team (es. tre Premium + un Super). Il tier è indipendente dal ruolo (Member/Admin). **Plus non è disponibile** per i team.
- **Usage incluso:** ogni posto pagato ha un **pool settimanale** per i modelli Cursor (es. Grok 4.7, Grok 4.6, Grok 4.5, Composer 2.5), condiviso tra Cursor e Grok **per quella persona**. I pool **non si trasferiscono** tra membri e si **rinnovano settimanalmente**.
- **Usage aggiuntivo:** modelli third-party e consumo Cursor oltre il pool settimanale sono addebitati su un **saldo prepagato condiviso** dal team (top-up una tantum o **Auto Top-up**), con **Cursor Token Rate** su richieste third-party eleggibili ($0,25 / milione di token, salvo esenzioni documentate). Limiti mensili configurabili a livello team e per membro.
- **Legacy Teams** (team creati prima di The X Subscription): possono restare su **Standard $40/posto/mese** e **Premium $120/posto/mese** (5× allowance Standard), con pool mensili legacy e on-demand; possibile migrazione unificata su richiesta del team owner/admin.

### 4.2 Funzionalità incluse (Teams)

- Fatturazione e amministrazione **centralizzate**; statistiche usage e **Admin API**.
- **SSO** SAML/OIDC.
- **Privacy Mode** imposto **obbligatoriamente** a livello team.
- **Retention dati chat** personalizzabile.
- **Team marketplace** per rules, skills e plugin interni.
- **Bugbot** — code review agentica.
- **Cloud Agents** e **automazioni** con contesto team condiviso.
- **Usage analytics** per comprendere l’adozione e i costi.

### 4.3 Quando Teams è sufficiente

- Numero limitato o medio di sviluppatori con profili di utilizzo differenziati (mix Premium/Super/Ultra).
- SSO e privacy mode team-wide accettati come livello di controllo.
- Assenza di requisiti formali su **SCIM**, **audit log centralizzato**, **fatturazione PO**, **usage pooling** o **agenti self-hosted**.
- Disponibilità a gestire **top-up** e limiti sul saldo prepagato condiviso.

---

## 5. Piano Enterprise — dettaglio

### 5.1 Modello economico

- **Canale:** **custom** — nessun listino pubblico; preventivo via **contact sales** / **`enterprise@cursor.com`**.
- **Struttura tipica (come da documentazione pubblica):** pricing **per posto** con **allotment di usage incluso**; possibilità di **pre-commit** per team ad alto volume; amministratori con **limiti configurabili** a livello team e utente.
- **Differenza chiave vs Teams:** **usage pooled** condiviso tra tutti i membri (Grok e Cursor), adatto a team con utilizzo disomogeneo e necessità di ottimizzazione del costo per organizzazione.

### 5.2 Funzionalità incluse (Enterprise = Teams +)

**Amministrazione e billing**

- Usage **pooled** condiviso.
- Fatturazione tramite **fattura** e **Purchase Order**.
- **SCIM** — gestione posti e provisioning automatizzato.
- **Billing groups** per strutturare costi per divisione/progetto.
- **Service accounts**.

**Sicurezza, compliance e audit**

- **Audit logs**.
- **AI Code Tracking API** e analytics avanzate (**Conversation Insights**).
- **Cursor Blame** (tracciabilità contributi IA nel codice).
- **Customer Managed Encryption Keys (CMEK)**.
- Controlli amministrativi granulari:
  - **Model Access Control**
  - **Run Mode**
  - **Repository Blocklist**
  - **MCP Configuration**
- **Agent Sandbox Mode** enforce org-wide.
- **Hooks** — distribuzione MDM e **server-side** (logging, auditing).

**Agenti e marketplace**

- **Self-Hosted Machines** per Cloud Agents, inclusi **Team Pools**.
- Marketplace team **illimitati**; import plugin community **off by default**; modifiche marketplace solo admin; **distribuzione e access gating via SCIM** (gruppi IdP).

**Supporto**

- **Supporto prioritario** e **account manager** dedicato.

### 5.3 Quando Enterprise è raccomandato

Cursor raccomanda Enterprise quando servono, in modo non negoziabile:

- **Usage pooling** organizzativo;
- **Fatturazione enterprise** (fattura/PO);
- **SCIM** e provisioning automatizzato;
- **Sicurezza avanzata** (audit, CMEK, blocklist repo, restrizioni modelli/MCP, agenti on-prem/self-hosted);
- **Supporto prioritario** e relazione commerciale strutturata.

Per organizzazioni multi-team collegate, la documentazione suggerisce di valutare anche il modello **Organizations** (oltre al singolo team).

---

## 6. Pricing — sintesi per il CDA

### 6.1 Teams (prezzi pubblici indicativi)

| Voce | Importo (USD) | Note |
|------|---------------|------|
| Posto Premium | $30 / posto / mese | Pool usage settimanale Premium |
| Posto Super | $100 / posto / mese | ~3,75× Premium |
| Posto Ultra | $200 / posto / mese | ~12,5× Premium |
| Posto Free | $0 | Usage da piano personale membro |
| Usage aggiuntivo | Variabile | Saldo prepagato team + eventuale Cursor Token Rate su third-party |
| Legacy Standard / Premium | $40 / $120 | Solo team pre-esistenti non migrati |

**Esempio di ordine di grandezza (illustrativo, da calibrare sui FTE):**

- 10 sviluppatori Premium: **$300/mese** + eventuale usage aggiuntivo.
- 8 Premium + 2 Super: **$440/mese** + usage aggiuntivo.
- 1–2 “power user” Ultra + resto Premium: mix per bilanciare costo e picchi di utilizzo agentico.

La fatturazione **annuale** esiste per i piani individuali nella subscription X; per Teams verificare condizioni al momento dell’ordine.

### 6.2 Enterprise

- **Prezzo:** **su preventivo** — includere nel budget una voce “Cursor Enterprise” solo dopo RFP/quote formale.
- **Contatti:** `enterprise@cursor.com`, form **contact sales** su cursor.com.

### 6.3 Costi indiretti da budgetare

- Tempo amministrativo (SSO, policy, limiti usage, formazione).
- Eventuale **pre-commit usage** (Enterprise o team con forte uso third-party).
- Integrazione con processi **SDLC** (Bugbot, Cloud Agents, policy repository).

---

## 7. Sicurezza e compliance

| Tema | Teams | Enterprise |
|------|-------|------------|
| Autenticazione federata | SSO SAML/OIDC | SSO + SCIM |
| Privacy e dati chat | Privacy Mode **obbligatoria** team-wide; retention custom | Enforce org-wide; controlli estesi |
| Tracciabilità | Admin API, analytics usage | Audit logs, AI Code Tracking API, Cursor Blame |
| Controllo superficie d’attacco IA | Limiti team e usage | Blocklist repo, restrizioni modelli, MCP policy, sandbox enforce |
| Crittografia | Standard piattaforma | Opzione **CMEK** |
| Agenti in ambienti sensibili | Cloud agents gestiti Cursor | **Self-hosted** Cloud Agents / Team Pools |
| Marketplace / supply chain plugin | Team marketplace (1); community import default on | Marketplace illimitati; community import default off; gating SCIM |

**Implicazione per il CDA:** Teams copre SSO e privacy mode enforced; **Enterprise** è il livello da presupporre se policy interne richiedono audit trail formale, chiavi gestite dal cliente, whitelist/blacklist repository o agenti su infrastruttura controllata.

---

## 8. Governance e amministrazione

### 8.1 Teams

- Ruoli **Admin** / **Member**; tier posto indipendente dal ruolo.
- **Limiti di spesa:** tetto mensile usage aggiuntivo team + default e override per membro.
- **Visibilità:** dashboard usage (incluso percorso grok.com/dashboard per admin, secondo documentazione corrente).
- **Posti Free:** utili per stakeholder con piano personale già attivo; non contano sulla quota team.

### 8.2 Enterprise

- **Pooling** riduce rischio di “posto sottoutilizzato” vs collega sopra quota.
- **Billing groups** e **SCIM** allineano costi e accessi a struttura organizzativa (BU, paese, progetto).
- **Service accounts** per automazioni e integrazioni senza utente umano.
- **Policies centralizzate** su modelli, modalità di esecuzione agenti, MCP e repository.

### 8.3 Raccomandazioni operative (pre-approvazione)

1. Definire **numero posti attivi** e profilo (Premium / Super / Ultra) per ruolo (es. senior/architect → Super o Ultra).
2. Stabilire **owner amministrativo** (IT/Security + Engineering lead) per SSO, limiti usage e marketplace.
3. Documentare **policy d’uso** (dati sensibili, Privacy Mode, repository ammessi).
4. Per Enterprise: avviare **due diligence** commerciale e security con Cursor prima della delibera di importo.

---

## 9. Raccomandazione al Consiglio

### 9.1 Opzione A — Una suite **Teams** (prima fase)

**Indicata se:** l’obiettivo è dotare rapidamente 1–2 team di sviluppo con Cursor, con SSO, privacy mode obbligatoria e costi **prevedibili** per posto.

**Delibera tipo:** approvare l’acquisto di **N posti Teams** (specificare mix tier) per **12 mesi**, con plafond annuo per **usage aggiuntivo prepagato** (es. top-up trimestrale) e revisione a 6 mesi su analytics di adozione e spesa.

### 9.2 Opzione B — Due suite **Teams** (es. divisioni distinte)

**Indicata se:** servono **due team amministrativamente separati** (es. product vs platform, o due legal entity con billing distinto), mantenendo self-service.

**Delibera tipo:** approvare **due subscription Teams** con governance coordinata (SSO stesso IdP, policy allineate) e limiti usage per ciascun team.

### 9.3 Opzione C — **Enterprise** (o Teams + percorso upgrade)

**Indicata se:** requisiti non negoziabili includono **usage pooling**, **fattura/PO**, **SCIM**, **audit log**, **CMEK**, **controlli MCP/modelli/repo**, **Cloud Agents self-hosted**.

**Delibera tipo:** autorizzare **negoziazione** con Cursor (Enterprise) entro un **budget massimo** da definire a valle di preventivo; in alternativa approvare Teams **con clausola di upgrade** a Enterprise entro 90 giorni se la due diligence security lo impone.

### 9.4 Sintesi decisionale

| Criterio | Teams | Enterprise |
|----------|-------|------------|
| Time-to-value | Alto | Medio (ciclo sales) |
| Prevedibilità costo posto | Alta | Media (contratto) |
| Ottimizzazione usage disomogeneo | Bassa (pool per utente) | Alta (pooled) |
| Compliance “enterprise-grade” | Buona (SSO, privacy enforce) | Elevata (audit, SCIM, CMEK, self-hosted agents) |

**Raccomandazione di default per molte PMI/team corporate:** approvare **Teams** con mix **Premium/Super** (e 1–2 Ultra se necessario) per **una o due suite**, salvo checklist security che punti inequivocabilmente a **Enterprise**.

---

## 10. Allegati e riferimenti (documentazione Cursor)

- Team Pricing: https://cursor.com/docs/account/teams/pricing  
- Enterprise (help): https://cursor.com/help/account-and-billing/enterprise  
- Enterprise (docs): https://cursor.com/docs/enterprise  
- Pricing generale: https://cursor.com/help/account-and-billing/pricing  
- Enterprise commerciale: https://cursor.com/enterprise — contact sales / `enterprise@cursor.com`

---

## 11. Checklist per la delibera (da completare in sede di CDA)

- [ ] Numero posti attivi approvati: __________  
- [ ] Mix tier (Premium / Super / Ultra): __________  
- [ ] Numero suite Teams (1 o 2): __________  
- [ ] Plafond annuo usage aggiuntivo (top-up): __________  
- [ ] Piano scelto: ☐ Teams  ☐ Enterprise  ☐ Teams con opzione upgrade Enterprise  
- [ ] Owner amministrativo e referente security: __________  
- [ ] Data revisione mid-term (analytics + ROI): __________  

---

*Documento preparato per supporto decisionale interno. Non costituisce offerta commerciale Cursor.*
