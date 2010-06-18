/*
 * Copyright (c) Thomas Parker, 2010.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */
package pcgen.cdom.facet;

import java.util.List;

import pcgen.cdom.base.CDOMObject;
import pcgen.cdom.enumeration.CharID;
import pcgen.cdom.enumeration.ListKey;
import pcgen.cdom.reference.CDOMSingleRef;
import pcgen.core.WeaponProf;

/**
 * NaturalWeaponProfFacet is a Facet that tracks the WeaponProfs that have been
 * implicitly granted to a Player Character via NATURALATTACKS
 */
public class NaturalWeaponProfFacet extends
		AbstractSourcedListFacet<WeaponProf> implements
		DataFacetChangeListener<CDOMObject>
{

	/**
	 * Triggered when one of the Facets to which NaturalWeaponProfFacet listens
	 * fires a DataFacetChangeEvent to indicate a CDOMObject was added to a
	 * Player Character.
	 * 
	 * @param dfce
	 *            The DataFacetChangeEvent containing the information about the
	 *            change
	 * 
	 * @see pcgen.cdom.facet.DataFacetChangeListener#dataAdded(pcgen.cdom.facet.DataFacetChangeEvent)
	 */
	public void dataAdded(DataFacetChangeEvent<CDOMObject> dfce)
	{
		CDOMObject cdo = dfce.getCDOMObject();
		// Natural Weapon Proficiencies
		List<CDOMSingleRef<WeaponProf>> iwp = cdo
				.getListFor(ListKey.IMPLIED_WEAPONPROF);
		if (iwp != null)
		{
			CharID id = dfce.getCharID();
			for (CDOMSingleRef<WeaponProf> ref : iwp)
			{
				add(id, ref.resolvesTo(), cdo);
			}
		}
	}

	/**
	 * Triggered when one of the Facets to which NaturalWeaponProfFacet listens
	 * fires a DataFacetChangeEvent to indicate a CDOMObject was removed from a
	 * Player Character.
	 * 
	 * @param dfce
	 *            The DataFacetChangeEvent containing the information about the
	 *            change
	 * 
	 * @see pcgen.cdom.facet.DataFacetChangeListener#dataRemoved(pcgen.cdom.facet.DataFacetChangeEvent)
	 */
	public void dataRemoved(DataFacetChangeEvent<CDOMObject> dfce)
	{
		removeAll(dfce.getCharID(), dfce.getCDOMObject());
	}

}
