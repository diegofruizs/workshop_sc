/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.sportcenter.resources;

import co.edu.uniandes.csw.sportcenter.ejb.SportLogic;
import co.edu.uniandes.csw.sportcenter.dtos.SportDetailDTO;
import co.edu.uniandes.csw.sportcenter.entities.SportEntity;
import co.edu.uniandes.csw.sportcenter.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sportcenter.persistence.SportPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "sports".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "sports". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/sports"
 *
 * @author ISIS2603
 *
 */
@Path("sports")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SportResource {

    @Inject
    SportLogic sportLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(SportPersistence.class.getName());


    @POST
    public SportDetailDTO createSport(SportDetailDTO sport) throws BusinessLogicException {
        SportEntity sportEntity = sport.toEntity();
        SportEntity nuevoSport = sportLogic.createSport(sportEntity);
        return new SportDetailDTO(nuevoSport);
    }


    @GET
    public List<SportDetailDTO> getSports() throws BusinessLogicException {
        return listEntity2DetailDTO(sportLogic.getSports());
    }

    @GET
    @Path("{id: \\d+}")
    public SportDetailDTO getSport(@PathParam("id") Long id) throws BusinessLogicException {
        SportEntity entity = sportLogic.getSport(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /sports/" + id + " no existe.", 404);
        }
        return new SportDetailDTO(sportLogic.getSport(id));
    }

    @PUT
    @Path("{id: \\d+}")
    public SportDetailDTO updateSport(@PathParam("id") Long id, SportDetailDTO sport) throws BusinessLogicException {
        sport.setId(id);
        SportEntity entity = sportLogic.getSport(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /sports/" + id + " no existe.", 404);
        }
        return new SportDetailDTO(sportLogic.updateSport(id, sport.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteSport(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una sport con id {0}", id);
        SportEntity entity = sportLogic.getSport(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /sports/" + id + " no existe.", 404);
        }
        sportLogic.deleteSport(id);
    }

    private List<SportDetailDTO> listEntity2DetailDTO(List<SportEntity> entityList) {
        List<SportDetailDTO> list = new ArrayList<>();
        for (SportEntity entity : entityList) {
            list.add(new SportDetailDTO(entity));
        }
        return list;
    }

}
