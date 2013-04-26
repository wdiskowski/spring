package de.modulware.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import de.modulware.domain.Benutzer;
import de.modulware.domain.BenutzerKitaRecht;
import de.modulware.domain.Kind;
import de.modulware.domain.Kita;
import de.modulware.domain.PermissionType;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

	protected static Logger logger = Logger
			.getLogger(CustomPermissionEvaluator.class);

	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permissionTypeName) {
		logger.debug("Evaluating expression using hasPermission signature");
		boolean hasObjectPermission = false;
		if (targetDomainObject == null) {
			hasObjectPermission = true;
		}
		if (targetDomainObject instanceof Kind) {
			hasObjectPermission = hasKitaPermission(authentication,
					((Kind) targetDomainObject).getKita(),
					PermissionType.valueOf((String)permissionTypeName));
		} else if (targetDomainObject instanceof Kita) {
			hasObjectPermission = hasKitaPermission(authentication,
					(Kita) targetDomainObject,
					PermissionType.valueOf((String)permissionTypeName));
		} else {
			throw new IllegalArgumentException("unknown entity: "
					+ targetDomainObject.getClass().getName());
		}

		return hasObjectPermission;
	}

	private boolean hasKitaPermission(Authentication authentication,
			Kita kita, PermissionType permissionType) {
		boolean hasPermission = false;
		for(BenutzerKitaRecht permission: ((Benutzer)authentication.getPrincipal()).getPermissions()){
			if(kita.getId().equals(permission.getKita().getId()) && permission.getRecht() == permissionType){
				hasPermission = true;
			}
		}
		return hasPermission;
	}

	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {

		throw new IllegalStateException("Not implementet yet");
	}

}