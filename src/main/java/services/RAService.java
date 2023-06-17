package services;

import odels.AbstractAcademico;
public class RAService extends AbstractB{

    public void validarRA(String ra) throws CampoLimiteTamanhoException, ValorInformadoRAException {
        if (ra == null || ra.isEmpty()) {
            throw new ValorInformadoRAException(ra, "RA");
        }

        if (ra.length() > 8) {
            throw new CampoLimiteTamanhoException("RA", "8");
        }

        if (!ValidadorCampoNumerico.isCampoNumericoValido(ra)) {
            throw new ValorInformadoRAException(ra, "RA");
        }
    }
}
