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

#ifndef ATTRIBUTECONFIG_SCHEMA_HXX
#define ATTRIBUTECONFIG_SCHEMA_HXX

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
  namespace AttributeConfigSchema
  {
    class AttributeConfigType;
    class RequestedAttributeType;
  }
}


#include <memory>    // std::auto_ptr
#include <algorithm> // std::binary_search

#include <xsd/cxx/tree/exceptions.hxx>
#include <xsd/cxx/tree/elements.hxx>
#include <xsd/cxx/tree/containers.hxx>
#include <xsd/cxx/tree/list.hxx>

#include "saml-schema-assertion-2.0.hxx"

namespace middleware
{
  namespace AttributeConfigSchema
  {
    class AttributeConfigType: public ::xml_schema::type
    {
      public:

      struct _xsd_AttributeConfigType
      {
        typedef ::xml_schema::type base_;
      };

      // RequestedAttribute
      // 
      public:
      struct RequestedAttribute
      {
        typedef ::middleware::AttributeConfigSchema::RequestedAttributeType type;
        typedef ::xsd::cxx::tree::traits< type, wchar_t > traits;
        typedef ::xsd::cxx::tree::sequence< type > container;
        typedef container::iterator iterator;
        typedef container::const_iterator const_iterator;
      };

      const RequestedAttribute::container&
      RequestedAttribute () const;

      RequestedAttribute::container&
      RequestedAttribute ();

      void
      RequestedAttribute (const RequestedAttribute::container&);

      // Constructors.
      //
      public:
      AttributeConfigType ();

      AttributeConfigType (const ::xercesc::DOMElement&,
                           ::xml_schema::flags = 0,
                           ::xml_schema::type* = 0);

      AttributeConfigType (const AttributeConfigType&,
                           ::xml_schema::flags = 0,
                           ::xml_schema::type* = 0);

      virtual AttributeConfigType*
      _clone (::xml_schema::flags = 0,
              ::xml_schema::type* = 0) const;

      // Implementation.
      //
      private:
      void
      parse (const ::xercesc::DOMElement&, ::xml_schema::flags);

      ::xsd::cxx::tree::sequence< RequestedAttribute::type > _xsd_RequestedAttribute_;
    };

    class RequestedAttributeType: public ::saml2::assertion::AttributeType
    {
      public:

      struct _xsd_RequestedAttributeType
      {
        typedef ::saml2::assertion::AttributeType base_;
      };

#if defined(__EDG_VERSION__) || (defined(__HP_aCC) && __HP_aCC >= 60000)
      public:
      using _xsd_RequestedAttributeType::base_::Name;
#endif

      // Constructors.
      //
      public:
      RequestedAttributeType ();

      RequestedAttributeType (const Name::type&);

      RequestedAttributeType (const ::xercesc::DOMElement&,
                              ::xml_schema::flags = 0,
                              ::xml_schema::type* = 0);

      RequestedAttributeType (const RequestedAttributeType&,
                              ::xml_schema::flags = 0,
                              ::xml_schema::type* = 0);

      virtual RequestedAttributeType*
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
  namespace AttributeConfigSchema
  {
    // Read from a URI or a local file.
    //

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (const ::std::basic_string< wchar_t >&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (const ::std::basic_string< wchar_t >&,
                     ::xsd::cxx::xml::error_handler< wchar_t >&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (const ::std::basic_string< wchar_t >&,
                     ::xercesc::DOMErrorHandler&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());


    // Read from std::istream.
    //

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (::std::istream&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (::std::istream&,
                     ::xsd::cxx::xml::error_handler< wchar_t >&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (::std::istream&,
                     ::xercesc::DOMErrorHandler&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());


    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (::std::istream&,
                     const ::std::basic_string< wchar_t >& id,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (::std::istream&,
                     const ::std::basic_string< wchar_t >& id,
                     ::xsd::cxx::xml::error_handler< wchar_t >&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (::std::istream&,
                     const ::std::basic_string< wchar_t >& id,
                     ::xercesc::DOMErrorHandler&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());


    // Read from InputSource.
    //

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (const ::xercesc::DOMInputSource&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (const ::xercesc::DOMInputSource&,
                     ::xsd::cxx::xml::error_handler< wchar_t >&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (const ::xercesc::DOMInputSource&,
                     ::xercesc::DOMErrorHandler&,
                     ::xml_schema::flags = 0,
                     const ::xsd::cxx::tree::properties< wchar_t >& = ::xsd::cxx::tree::properties< wchar_t > ());


    // Read from DOM.
    //

    ::std::auto_ptr< ::middleware::AttributeConfigSchema::AttributeConfigType >
    AttributeConfig (const ::xercesc::DOMDocument&,
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
  namespace AttributeConfigSchema
  {
    // Serialize to an existing DOM instance.
    //
    void
    AttributeConfig (::xercesc::DOMDocument&,
                     const ::middleware::AttributeConfigSchema::AttributeConfigType&,
                     ::xml_schema::flags = 0);


    // Serialize to a new DOM instance.
    //
    ::xsd::cxx::xml::dom::auto_ptr< ::xercesc::DOMDocument >
    AttributeConfig (const ::middleware::AttributeConfigSchema::AttributeConfigType&, 
                     const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                     ::xml_schema::flags = 0);


    // Serialize to XMLFormatTarget.
    //
    void
    AttributeConfig (::xercesc::XMLFormatTarget&,
                     const ::middleware::AttributeConfigSchema::AttributeConfigType&, 
                     const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                     const ::std::basic_string< wchar_t >& = L"UTF-8",
                     ::xml_schema::flags = 0);


    void
    AttributeConfig (::xercesc::XMLFormatTarget&,
                     const ::middleware::AttributeConfigSchema::AttributeConfigType&, 
                     const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                     ::xsd::cxx::xml::error_handler< wchar_t >&,
                     const ::std::basic_string< wchar_t >& = L"UTF-8",
                     ::xml_schema::flags = 0);

    void
    AttributeConfig (::xercesc::XMLFormatTarget&,
                     const ::middleware::AttributeConfigSchema::AttributeConfigType&, 
                     const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                     ::xercesc::DOMErrorHandler&,
                     const ::std::basic_string< wchar_t >& = L"UTF-8",
                     ::xml_schema::flags = 0);


    // Serialize to std::ostream.
    //
    void
    AttributeConfig (::std::ostream&,
                     const ::middleware::AttributeConfigSchema::AttributeConfigType&, 
                     const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                     const ::std::basic_string< wchar_t >& = L"UTF-8",
                     ::xml_schema::flags = 0);


    void
    AttributeConfig (::std::ostream&,
                     const ::middleware::AttributeConfigSchema::AttributeConfigType&, 
                     const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                     ::xsd::cxx::xml::error_handler< wchar_t >&,
                     const ::std::basic_string< wchar_t >& = L"UTF-8",
                     ::xml_schema::flags = 0);

    void
    AttributeConfig (::std::ostream&,
                     const ::middleware::AttributeConfigSchema::AttributeConfigType&, 
                     const ::xsd::cxx::xml::dom::namespace_infomap< wchar_t >&,
                     ::xercesc::DOMErrorHandler&,
                     const ::std::basic_string< wchar_t >& = L"UTF-8",
                     ::xml_schema::flags = 0);


    void
    operator<< (::xercesc::DOMElement&,
                const AttributeConfigType&);

    void
    operator<< (::xercesc::DOMElement&,
                const RequestedAttributeType&);
  }
}

#include <xsd/cxx/post.hxx>

// Begin epilogue.
//
//
// End epilogue.

#endif // ATTRIBUTECONFIG_SCHEMA_HXX
