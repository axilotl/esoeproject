// Copyright (C) 2005-2007 Code Synthesis Tools CC
//
// This program was generated by XML Schema Definition Compiler (XSD)
//
// Licensed under the Apache License, Version 2.0 (the "License"); you may not 
// use this file except in compliance with the License. You may obtain a copy of 
// the License at 
// 
//   http://www.apache.org/licenses/LICENSE-2.0 
// 
// Unless required by applicable law or agreed to in writing, software 
// distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
// WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
// License for the specific language governing permissions and limitations under 
// the License.

#ifndef CACHECLEAR_SCHEMA_SAML_METADATA_HXX
#define CACHECLEAR_SCHEMA_SAML_METADATA_HXX

#include <xsd/cxx/version.hxx>

#if (XSD_INT_VERSION != 2030100L)
#error XSD runtime version mismatch
#endif

// Begin prologue.
//
//
// End prologue.

#include <xsd/cxx/pre.hxx>

#ifndef XSD_USE_WCHAR
#define XSD_USE_WCHAR
#endif

#ifndef XSD_CXX_TREE_USE_WCHAR
#define XSD_CXX_TREE_USE_WCHAR
#endif

#include "xsd/xml-schema.hxx"

// Forward declarations.
//
namespace middleware
{
  namespace cacheClearServiceSchema
  {
    class CacheClearServiceType;
  }
}


#include <memory>    // std::auto_ptr
#include <algorithm> // std::binary_search

#include <xsd/cxx/tree/exceptions.hxx>
#include <xsd/cxx/tree/elements.hxx>
#include <xsd/cxx/tree/containers.hxx>
#include <xsd/cxx/tree/list.hxx>

#include "saml-schema-metadata-2.0.hxx"

namespace middleware
{
  namespace cacheClearServiceSchema
  {
    class CacheClearServiceType: public ::saml2::metadata::IndexedEndpointType
    {
      public:

      struct _xsd_CacheClearServiceType
      {
        typedef ::saml2::metadata::IndexedEndpointType base_;
      };

#if defined(__EDG_VERSION__) || (defined(__HP_aCC) && __HP_aCC >= 60000)
      public:
      using _xsd_CacheClearServiceType::base_::Binding;
      using _xsd_CacheClearServiceType::base_::Location;
      using _xsd_CacheClearServiceType::base_::index;
#endif

      // Constructors.
      //
      public:
      CacheClearServiceType ();

      CacheClearServiceType (const Binding::type&,
                             const Location::type&,
                             const index::type&);

      CacheClearServiceType (const ::xercesc::DOMElement&,
                             ::xml_schema::flags = 0,
                             ::xml_schema::type* = 0);

      CacheClearServiceType (const CacheClearServiceType&,
                             ::xml_schema::flags = 0,
                             ::xml_schema::type* = 0);

      virtual CacheClearServiceType*
      _clone (::xml_schema::flags = 0,
              ::xml_schema::type* = 0) const;
    };
  }
}

#include <iosfwd>

#include <xercesc/dom/DOMDocument.hpp>
#include <xercesc/dom/DOMInputSource.hpp>
#include <xercesc/dom/DOMErrorHandler.hpp>

namespace middleware
{
  namespace cacheClearServiceSchema
  {
    // Read from a URI or a local file.
    //

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (const ::std::basic_string< wchar_t >&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (const ::std::basic_string< wchar_t >&,
                       ::xsd::cxx::xml::error_handler< wchar_t >&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (const ::std::basic_string< wchar_t >&,
                       ::xercesc::DOMErrorHandler&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());


    // Read from std::istream.
    //

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (::std::istream&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (::std::istream&,
                       ::xsd::cxx::xml::error_handler< wchar_t >&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (::std::istream&,
                       ::xercesc::DOMErrorHandler&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());


    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (::std::istream&,
                       const ::std::basic_string< wchar_t >& id,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (::std::istream&,
                       const ::std::basic_string< wchar_t >& id,
                       ::xsd::cxx::xml::error_handler< wchar_t >&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (::std::istream&,
                       const ::std::basic_string< wchar_t >& id,
                       ::xercesc::DOMErrorHandler&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());


    // Read from InputSource.
    //

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (const ::xercesc::DOMInputSource&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (const ::xercesc::DOMInputSource&,
                       ::xsd::cxx::xml::error_handler< wchar_t >&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (const ::xercesc::DOMInputSource&,
                       ::xercesc::DOMErrorHandler&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());


    // Read from DOM.
    //

    ::std::auto_ptr< ::middleware::cacheClearServiceSchema::CacheClearServiceType >
    CacheClearService (const ::xercesc::DOMDocument&,
                       ::xml_schema::flags = 0,
                       const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());
  }
}

#include <iosfwd> // std::ostream&

#include <xercesc/dom/DOMDocument.hpp>
#include <xercesc/dom/DOMErrorHandler.hpp>
#include <xercesc/framework/XMLFormatter.hpp>

#include <xsd/cxx/xml/dom/auto-ptr.hxx>

namespace middleware
{
  namespace cacheClearServiceSchema
  {
    // Serialize to an existing DOM instance.
    //
    void
    CacheClearService (::xercesc::DOMDocument&,
                       const ::middleware::cacheClearServiceSchema::CacheClearServiceType&,
                       ::xml_schema::flags = 0);


    // Serialize to a new DOM instance.
    //
    ::xsd::cxx::xml::dom::auto_ptr< ::xercesc::DOMDocument >
    CacheClearService (const ::middleware::cacheClearServiceSchema::CacheClearServiceType&, 
                       const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                       ::xml_schema::flags = 0);


    // Serialize to XMLFormatTarget.
    //
    void
    CacheClearService (::xercesc::XMLFormatTarget&,
                       const ::middleware::cacheClearServiceSchema::CacheClearServiceType&, 
                       const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                       const ::std::basic_string< wchar_t >& = L"UTF-8",
                       ::xml_schema::flags = 0);


    void
    CacheClearService (::xercesc::XMLFormatTarget&,
                       const ::middleware::cacheClearServiceSchema::CacheClearServiceType&, 
                       const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                       ::xsd::cxx::xml::error_handler< wchar_t >&,
                       const ::std::basic_string< wchar_t >& = L"UTF-8",
                       ::xml_schema::flags = 0);

    void
    CacheClearService (::xercesc::XMLFormatTarget&,
                       const ::middleware::cacheClearServiceSchema::CacheClearServiceType&, 
                       const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                       ::xercesc::DOMErrorHandler&,
                       const ::std::basic_string< wchar_t >& = L"UTF-8",
                       ::xml_schema::flags = 0);


    // Serialize to std::ostream.
    //
    void
    CacheClearService (::std::ostream&,
                       const ::middleware::cacheClearServiceSchema::CacheClearServiceType&, 
                       const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                       const ::std::basic_string< wchar_t >& = L"UTF-8",
                       ::xml_schema::flags = 0);


    void
    CacheClearService (::std::ostream&,
                       const ::middleware::cacheClearServiceSchema::CacheClearServiceType&, 
                       const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                       ::xsd::cxx::xml::error_handler< wchar_t >&,
                       const ::std::basic_string< wchar_t >& = L"UTF-8",
                       ::xml_schema::flags = 0);

    void
    CacheClearService (::std::ostream&,
                       const ::middleware::cacheClearServiceSchema::CacheClearServiceType&, 
                       const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                       ::xercesc::DOMErrorHandler&,
                       const ::std::basic_string< wchar_t >& = L"UTF-8",
                       ::xml_schema::flags = 0);


    void
    operator<< (::xercesc::DOMElement&,
                const CacheClearServiceType&);
  }
}

#include <xsd/cxx/post.hxx>

// Begin epilogue.
//
//
// End epilogue.

#endif // CACHECLEAR_SCHEMA_SAML_METADATA_HXX
