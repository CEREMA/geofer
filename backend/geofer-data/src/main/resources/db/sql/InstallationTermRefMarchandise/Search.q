@comments
Search 

@params
- Integer p_installation_term_id

@values
p_installation_term_id=3

@sql
SELECT 
    installation_term_ref_marchandise.id,
    installation_term_ref_marchandise.ref_marchandise_id,
    installation_term_ref_marchandise.si_par_train,
    installation_term_ref_marchandise.flux,
    ref_marchandise.code_nst,
    ref_marchandise.libelle
FROM 
    installation_term_ref_marchandise
JOIN 
    ref_marchandise ON ref_marchandise.id = installation_term_ref_marchandise.ref_marchandise_id
WHERE 
    installation_term_ref_marchandise.installation_term_id = :p_installation_term_id
    AND 
    installation_term_ref_marchandise.si_par_train = true

@return List<Object[]>