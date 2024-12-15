# Vereinfachende Annahmen

- keine Mandantenfähigkeit
    - braucht dedizierten Mandanten-Identifikator
- Single Machine
- Pro Monat und Mitarbeiter werden nur Stunden und Minuten erfasst

# Später
- Berechtigungen in AktualisiereMonatsArbeitsstunden überprüfen (aktuell nur in Spring Ressource)
- HTTP 401 und HTTP 403 in ArbeitsstundenRessource sichtbar machen (aktuell in SpringSecurityKonfiguration) 
- Global Exception Handler for HTTP 500 (follow up)
  - https://stackoverflow.com/questions/78055556/why-is-my-spring-security-config-throwing-403-on-permitall-endpoints-also
  - https://www.baeldung.com/exception-handling-for-rest-with-spring
  - make generic HTTP 500 less verbose

