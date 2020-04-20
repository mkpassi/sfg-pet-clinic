package guru.spring5framework.sfgpetclinic.services;

import guru.spring5framework.sfgpetclinic.model.BaseEntity;
import guru.spring5framework.sfgpetclinic.model.PetType;

public interface PetTypeService<PetType extends BaseEntity,Long extends Number> extends CrudService<PetType,Long> {
}
