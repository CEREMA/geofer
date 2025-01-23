package fr.cerema.dsi.geofer.config;

import fr.cerema.dsi.commons.exceptions.GenericServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;


// TODO Voir au fur et a mesure de cet aspect

@Component
@Aspect
public class AspectJConfiguration {

    private static Logger logger = LoggerFactory.getLogger(AspectJConfiguration.class);


    @Around("execution(* fr.cerema.dsi.commons.services.*Service+.*(..)) || " +
            "execution(* fr.cerema.dsi.geofer.services.*Service+.*(..)) ")
    public Object executeService(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = null;
        logger.trace("Appel du service {}", joinPoint.getSignature().toShortString());
        try {
            returnValue = joinPoint.proceed();
            logger.debug("Succès de l'appel à {}", joinPoint.getSignature().toShortString());
        } catch (AuthenticationServiceException ase) {
            logger.warn("Warn lors de l'appel au service d'authentification {}", joinPoint.getSignature().toShortString());
            throw ase;
        } catch (GenericServiceException gse) {
            logger.warn("Warn lors de l'appel à {} : {}", joinPoint.getSignature().toShortString(), gse.getMessage());
            throw gse;
        } catch (RuntimeException rte) {
            logger.error("Erreur lors de l'appel à {} : {}", joinPoint.getSignature().toShortString(), rte.getMessage());
            throw rte;
        }
        return returnValue;
    }


    @Around("execution(* fr.cerema.dsi.commons.repositories.*Repository+.*(..)) || " +
            "execution(* fr.cerema.dsi.geofer.repositories.*Repository+.*(..))")
    public Object executeRepository(final ProceedingJoinPoint joinPoint) throws Throwable {
        logger.trace("Appel de {}", joinPoint.getSignature().toShortString());
        try {
            Object returnValue = joinPoint.proceed();
            logger.debug("Succès de l'appel à {}", joinPoint.getSignature().toShortString());
            return returnValue;
        } catch (RuntimeException e) {
            logger.error("L'appel à {} a provoqué une erreur : {}", joinPoint.getSignature().toShortString(), e.getMessage());
            throw e;
        }
    }

}
