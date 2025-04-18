"use client";
import { Input } from "@/components/ui/input";
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Controller, SubmitHandler, useForm } from "react-hook-form";
import { getSimilar, postPhonetic } from "@/http/Phonetics";
import { useEffect, useState } from "react";
import { IPhoneticResponse } from "@/interfaces/IPhonetic";
import { Button } from "@/components/ui/button";

type Inputs = {
  name: string;
};

export default function Home() {
  const [list, setList] = useState<IPhoneticResponse[]>([]);

  const {
    control,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<Inputs>();

  const nameValue = watch("name");

  const onSubmit = async (data: Inputs) => {
    try {
      const res = await postPhonetic({ name: data.name });
      setList((prevList) => [...prevList, res]);
    } catch (error) {
      console.error("Error posting phonetic:", error);
    }
  };

  useEffect(() => {
    const fetch = async () => {
      try {
        const res = await getSimilar({ name: nameValue });
        setList(res);
      } catch (error) {
        console.error("Error fetching similar phonetics:", error);
      }
    };

    fetch();
  }, [nameValue]);

  return (
    <div className="flex items-center justify-center h-screen flex-col  gap-10">
      <h1 className="text-2xl font-black">Fuzzy Phonetic Name</h1>
      <form onSubmit={handleSubmit(onSubmit)} className="flex flex-row gap-4">
        <Controller
          name="name"
          control={control}
          render={({ field }) => (
            <Input {...field} className="w-100" placeholder="Fulano de Town" />
          )}
        />

        <Button type="submit">Adicionar</Button>
      </form>

      <div className="w-180">
        <Table>
          <TableCaption>Resultados da sua pesquisa</TableCaption>
          <TableHeader>
            <TableRow>
              <TableHead className="w-[100px]">Id</TableHead>
              <TableHead>Name</TableHead>
              <TableHead className="text-right">Hash</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {list.map((item) => (
              <TableRow>
                <TableCell className="font-medium">{item.id}</TableCell>
                <TableCell>{item.name}</TableCell>
                <TableCell className="text-right">{item.code}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}
